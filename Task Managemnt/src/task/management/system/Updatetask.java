package task.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Updatetask extends JFrame implements ActionListener {
    private JTextField taskNameField, taskTypeField, taskTimeField;
    private JDateChooser taskDateChooser;
    private JButton updateButton, backButton;
    private JLabel taskIdLabel;

    private String taskIdToUpdate;

    public Updatetask(String taskId) {
        this.taskIdToUpdate = taskId;

        setTitle("Update Task Detail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(null);

        JLabel heading = new JLabel("Update Task Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 25));
        add(heading);

        JLabel taskNameLabel = new JLabel("Task Name:");
        taskNameLabel.setBounds(50, 150, 150, 30);
        add(taskNameLabel);

        taskNameField = new JTextField();
        taskNameField.setBounds(200, 150, 150, 30);
        add(taskNameField);

        JLabel taskTypeLabel = new JLabel("Task Type:");
        taskTypeLabel.setBounds(400, 150, 150, 30);
        add(taskTypeLabel);

        taskTypeField = new JTextField();
        taskTypeField.setBounds(600, 150, 150, 30);
        add(taskTypeField);

        JLabel taskDateLabel = new JLabel("Date of Task:");
        taskDateLabel.setBounds(50, 200, 150, 30);
        add(taskDateLabel);

        taskDateChooser = new JDateChooser();
        taskDateChooser.setBounds(200, 200, 150, 30);
        add(taskDateChooser);

        JLabel taskTimeLabel = new JLabel("Task Time:");
        taskTimeLabel.setBounds(400, 200, 150, 30);
        add(taskTimeLabel);

        taskTimeField = new JTextField();
        taskTimeField.setBounds(600, 200, 150, 30);
        add(taskTimeField);

        taskIdLabel = new JLabel("Task ID:");
        taskIdLabel.setBounds(50, 250, 150, 30);
        add(taskIdLabel);

        JLabel taskIdValueLabel = new JLabel(taskIdToUpdate);
        taskIdValueLabel.setBounds(200, 250, 150, 30);
        add(taskIdValueLabel);

        updateButton = new JButton("UPDATE");
        updateButton.setBounds(450, 300, 150, 40);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("BACK");
        backButton.setBounds(250, 300, 150, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        // Fetch task details from database and populate the fields
        try {
            conn c = new conn();
            String query = "SELECT * FROM task WHERE taskId = '" + taskId + "'";
            ResultSet resultSet = c.statement.executeQuery(query);
            if (resultSet.next()) {
                taskNameField.setText(resultSet.getString("taskName"));
                taskTypeField.setText(resultSet.getString("taskType"));
                taskDateChooser.setDate(resultSet.getDate("taskDate"));
                taskTimeField.setText(resultSet.getString("taskTime"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String taskName = taskNameField.getText();
            String taskType = taskTypeField.getText();
            String taskDate = ((JTextField) taskDateChooser.getDateEditor().getUiComponent()).getText();
            String taskTime = taskTimeField.getText();

            try {
                conn c = new conn();
                String query = "UPDATE task SET taskName = '" + taskName + "', taskType = '" + taskType + "', taskDate = '" + taskDate + "', taskTime = '" + taskTime + "' WHERE taskId = '" + taskIdToUpdate + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Task details updated successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        // This is only for testing purpose, not required in actual use
        new Updatetask("");
    }
}
