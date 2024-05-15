package task.management.system;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddTask extends JFrame implements ActionListener {

    private JTextField taskNameField, taskTypeField;
    private JDateChooser taskDateChooser;
    private JComboBox<String> taskTimeComboBox;
    private JButton addButton, backButton;

    private Random random = new Random();
    private int taskId = random.nextInt(999999);

    public AddTask() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Task Detail");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        getContentPane().setBackground(new Color(173, 216, 230)); // Set light blue background color
        setLayout(null); // Use absolute positioning

        JLabel heading = new JLabel("Add Task Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 25));
        add(heading);

        JLabel taskNameLabel = new JLabel("Task Name:");
        taskNameLabel.setBounds(50, 150, 150, 30);
        add(taskNameLabel);

        taskNameField = new JTextField();
        taskNameField.setBounds(200, 150, 150, 30);
        add(taskNameField);

        JLabel taskTypeLabel = new JLabel("Task Type):");
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

        String[] taskTimes = {
                "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM",
                "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM",
                "10:00 PM", "11:00 PM"
        };
        taskTimeComboBox = new JComboBox<>(taskTimes);
        taskTimeComboBox.setBounds(600, 200, 150, 30);
        add(taskTimeComboBox);

        addButton = new JButton("ADD");
        addButton.setBounds(450, 250, 150, 40);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("BACK");
        backButton.setBounds(250, 250, 150, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String taskName = taskNameField.getText();
            String taskType = taskTypeField.getText();
            String taskDate = ((JTextField) taskDateChooser.getDateEditor().getUiComponent()).getText();
            String taskTime = (String) taskTimeComboBox.getSelectedItem();

            try {
                conn c = new conn();
                String query = "INSERT INTO task (taskName, taskType, taskDate, taskTime, taskId) " +
                        "VALUES ('" + taskName + "', '" + taskType + "', '" + taskDate + "', '" + taskTime + "', '" + taskId + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Task details added successfully");
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
        new AddTask();
    }
}
