package lab11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {

    private static Connection connection;

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("db");
            String url = bundle.getString("db.url");
            String username = bundle.getString("db.username");
            String password = bundle.getString("db.password");

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to the database established");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
