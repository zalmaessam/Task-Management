package task.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Splash extends JFrame {

    private static final int SPLASH_DURATION = 5000; // 5 seconds
    private JProgressBar progressBar;

    public Splash() {
        initialize();
        simulateProgress();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Remove window decorations
        setSize(900, 700);
        setLocationRelativeTo(null); // Center the splash screen on the screen


        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(135, 206, 250)); // Light blue color
        progressBar.setBounds(150, 650, 600, 25);
        add(progressBar);

        setVisible(true);
    }

    private void simulateProgress() {
        Timer timer = new Timer(50, new ActionListener() {
            int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                progressBar.setValue(counter);

                if (counter == 100) {
                    ((Timer) e.getSource()).stop();
                    setVisible(false);
                    dispose();
                    new Login();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Splash();
    }
}
