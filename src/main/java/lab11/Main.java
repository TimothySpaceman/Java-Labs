package lab11;

import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        setupDatabase();
    }

    public static void setupDatabase(){
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS products (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "price DECIMAL)";
            stmt.executeUpdate(sql);
            System.out.println("Table \"products\" created successfully");
        } catch (Exception e) {
            System.out.println("Error while creating table");
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
