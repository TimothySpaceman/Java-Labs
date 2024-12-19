package lab11;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    public static Connection conn = null;

    public static void main(String[] args) {
        setupDatabase(false);
        insertProductsBatch();
    }

    public static void setupDatabase(boolean closeConnection) {
        try {
            conn = DatabaseConnection.getConnection();
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
            if(closeConnection) {
                System.out.println("Closing connection");
                DatabaseConnection.closeConnection();
            }
        }
    }

    private static void insertProductsBatch() {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (int i = 1; i <= 10; i += 1) {
                pstmt.setString(1, "Product " + i);
                pstmt.setBigDecimal(2, new java.math.BigDecimal(10 * i));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit();
            System.out.println("10 products inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error while inserting products");
            System.out.println(e.getMessage());
        }
    }

    private static void fetchAndDisplayProducts() {
        String sql = "SELECT id, name, price FROM products";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading products");
            System.out.println(e.getMessage());
        }
    }
}
