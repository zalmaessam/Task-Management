package task.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class conn {

    Connection connection;
    Statement statement;

    public conn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Taskmanagemnt","root","1234");
            statement = connection.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return null;
    }
}

/*
public class conn {
    public static void main(String[] args) {
        // JDBC URL for MySQL
        String url = "jdbc:mysql://localhost:3306/mydatabase";

        // Database credentials
        String username = "root";
        String password = "password";

        // Connection object
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute SQL queries
            // ...

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/