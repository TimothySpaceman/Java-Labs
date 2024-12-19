package lab11;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class Main {
    public static Connection conn = null;

    public static String[] brands = {"Samsung", "Google", "Lenovo", "HP"};
    public static String[] categories = {"Laptops", "Smartphones", "Displays"};

    public static void main(String[] args) {
        setupDatabase(false);
        insertProductsBatch();
        fetchAndDisplayProducts();
        addMoreProducts();
        fetchProductsByCategoryOrBrand("Laptops", "Lenovo");
        deleteAllRecords();
    }

    public static void setupDatabase(boolean closeConnection) {
        try {
            conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS products (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "brand VARCHAR(100), " +
                    "category VARCHAR(100), " +
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
        String sql = "INSERT INTO products (name, brand, category, price) VALUES (?, ?, ?, ?)";
        Random r = new Random();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (int i = 1; i <= 10; i += 1) {
                pstmt.setString(1, "Product " + i);
                pstmt.setString(2, brands[r.nextInt(brands.length)]);
                pstmt.setString(3, categories[r.nextInt(categories.length)]);
                pstmt.setBigDecimal(4, new java.math.BigDecimal(10 * i));
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
        String sql = "SELECT id, name, brand, category, price FROM products";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String category = rs.getString("category");
                BigDecimal price = rs.getBigDecimal("price");
                System.out.println("(" + id + ") " + name + " by " + brand + ", " + price + " [" + category + "]");
            }
        } catch (SQLException e) {
            System.out.println("Error while reading products");
            System.out.println(e.getMessage());
        }
    }

    public static void addMoreProducts() {
        String sql = "INSERT INTO products (name, brand, category, price) VALUES (?, ?, ?, ?)";
        Random r = new Random();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (int i = 11; i <= 15; i++) {
                pstmt.setString(1, "Product " + i);
                pstmt.setString(2, brands[r.nextInt(brands.length)]);
                pstmt.setString(3, categories[r.nextInt(categories.length)]);
                pstmt.setBigDecimal(4, new BigDecimal(20 * i));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit();
            System.out.println("5 products inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error while inserting additional products");
            System.out.println(e.getMessage());
        }
    }

    public static void fetchProductsByCategoryOrBrand(String searchCategory, String searchBrand) {
        String sql = "SELECT id, name, brand, category, price FROM products WHERE category LIKE ? OR brand LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchCategory + "%");
            pstmt.setString(2, "%" + searchBrand + "%");

            ResultSet rs = pstmt.executeQuery();

            System.out.println("Products from category \"" + searchCategory + "\" or brand \"" + searchBrand + "\":");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String category = rs.getString("category");
                BigDecimal price = rs.getBigDecimal("price");
                System.out.println("(" + id + ") " + name + " by " + brand + ", " + price + " [" + category + "]");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching products");
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAllRecords() {
        String sql = "DELETE FROM products WHERE true";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " records deleted from the table \"products\"");
        } catch (SQLException e) {
            System.out.println("Error while deleting records");
            System.out.println(e.getMessage());
        }
    }
}
