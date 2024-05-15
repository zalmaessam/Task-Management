package task.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class View_Task extends JFrame implements ActionListener {

    private JTable table;
    private JComboBox<String> choiceEmp;

    private TaskDAO taskDAO;

    public View_Task() {
        this.taskDAO = new TaskDAO();

        initializeUI();
        loadEmployeeIds();
        loadTaskTable();
    }

    private void initializeUI() {
        setTitle("Task Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        getContentPane().setBackground(new Color(39, 54, 82));
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 131, 122));
        JLabel searchLabel = new JLabel("Search by Task ID:");
        choiceEmp = new JComboBox<>();
        searchPanel.add(searchLabel);
        searchPanel.add(choiceEmp);

        JButton searchBtn = new JButton("Search");
        JButton printBtn = new JButton("Print");
        JButton updateBtn = new JButton("Update");
        JButton backBtn = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 131, 122));
        buttonPanel.add(searchBtn);
        buttonPanel.add(printBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(backBtn);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        searchBtn.addActionListener(this);
        printBtn.addActionListener(e -> {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        updateBtn.addActionListener(e -> {
            String selectedEmpId = (String) choiceEmp.getSelectedItem();
            setVisible(false);
        });
        backBtn.addActionListener(e -> {
            setVisible(false);
            new Main_class();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadEmployeeIds() {
        List<String> employeeIds = taskDAO.getAllEmployeeIds();
        for (String empId : employeeIds) {
            choiceEmp.addItem(empId);
        }
    }

    private void loadTaskTable() {
        try {
            String selectedEmpId = (String) choiceEmp.getSelectedItem();
            ResultSet resultSet = taskDAO.getTasksByEmployeeId(selectedEmpId);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            loadTaskTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(View_Task::new);
    }
}

class TaskDAO {
    private Connection conn;

    public TaskDAO() {
        // Initialize database connection
        this.conn = new conn().getConnection(); // Assuming `conn` class provides a getConnection() method
    }

    public List<String> getAllEmployeeIds() {
        List<String> employeeIds = new ArrayList<>();
        String query = "SELECT DISTINCT empId FROM task";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                employeeIds.add(rs.getString("empId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeIds;
    }

    public ResultSet getTasksByEmployeeId(String empId) throws SQLException {
        String query = "SELECT * FROM task WHERE empId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, empId);
        return stmt.executeQuery();
    }
}
