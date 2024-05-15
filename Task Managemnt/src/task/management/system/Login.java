/*package task.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login, back;

    Login(){

        JLabel username = new JLabel("Username");
        username.setBounds(40,20,100,30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(150,20,150,30);
        add(tusername);

        JLabel password = new JLabel("Password");
        password.setBounds(40,70,100,30);
        add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150,70,150,30);
        add(tpassword);

        login = new JButton("LOGIN");
        login.setBounds(150,140,150,30);
        login.setBackground(Color.black);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        back = new JButton("BACK");
        back.setBounds(150,180,150,30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i22 = i11.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        imgg.setBounds(350,10,600,400);
        add(imgg);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0,0,600,300);
        add(img);

        setSize(600,300);
        setLocation(450,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            try {
                String username = tusername.getText();
                String password = tpassword.getText();

                conn conn = new conn();
                String query = "select * from login where username = '"+ username +"' and password = '"+password+"'";
                ResultSet resultSet = conn.statement.executeQuery(query);
                if (resultSet.next()){
                    setVisible(false);
                    new Main_class();
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource() == back) {
            System.exit(90);
        }
    }
    // Inside the Login class, add a method to handle sign-up
    private void signUp() {
        String username = tusername.getText();
        String password = tpassword.getText();

        try {
            conn conn = new conn();
            String query = "INSERT INTO login (username, password) VALUES ('" + username + "', '" + password + "')";
            int rowsAffected = conn.statement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Sign-up successful! You can now log in.");
                // Clear text fields after successful sign-up
                tusername.setText("");
                tpassword.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Sign-up failed. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Modify actionPerformed method to handle sign-up button action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            // Handle login logic as before
        } else if (e.getSource() == back) {
            // Handle back button action
        } else if (e.getSource() == signUpButton) { // Assuming you have a signUpButton
            signUp(); // Call signUp method when sign-up button is clicked
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
*/
package task.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField tusername;
    JPasswordField tpassword;
    JButton login, signUp, back;

    Login(){

        JLabel username = new JLabel("Username");
        username.setBounds(40,20,100,30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(150,20,150,30);
        add(tusername);

        JLabel password = new JLabel("Password");
        password.setBounds(40,70,100,30);
        add(password);

        tpassword = new JPasswordField();
        tpassword.setBounds(150,70,150,30);
        add(tpassword);

        login = new JButton("LOGIN");
        login.setBounds(50,140,100,30);
        login.setBackground(Color.black);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        signUp = new JButton("SIGN UP");
        signUp.setBounds(160,140,100,30);
        signUp.setBackground(Color.black);
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);
        add(signUp);

        back = new JButton("BACK");
        back.setBounds(270,140,100,30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0,0,600,300);
        add(img);

        setSize(600,300);
        setLocation(450,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            // Handle login logic
            login();
        } else if (e.getSource() == signUp) {
            // Handle sign-up logic
            signUp();
        } else if (e.getSource() == back) {
            System.exit(90);
        }
    }

    private void login() {
        String username = tusername.getText();
        String password = String.valueOf(tpassword.getPassword());
//  To link info to the database.
        try {
            conn conn = new conn();
            String query = "select * from login where username = '"+ username +"' and password = '"+password+"'";
            ResultSet resultSet = conn.statement.executeQuery(query);
            if (resultSet.next()){
                setVisible(false);
                new Main_class();
            } else {
                JOptionPane.showMessageDialog(null,"Invalid username or password");
            }

        } catch (Exception E){
            E.printStackTrace();
        }
    }

    private void signUp() {
        String username = tusername.getText();
        String password = String.valueOf(tpassword.getPassword());

        try {
            conn conn = new conn();
            String query = "INSERT INTO login (username, password) VALUES ('" + username + "', '" + password + "')";
            int rowsAffected = conn.statement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Sign-up successful! You can now log in.");
                // Clear text fields after successful sign-up
                tusername.setText("");
                tpassword.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Sign-up failed. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
