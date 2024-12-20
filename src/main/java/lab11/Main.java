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
        performTransactionWithSavepoint();
        fetchAndDisplayProducts();
        deleteAllRecords();

        ProductDAO productDAO = new ProductDAO(conn);

        Product product1 = new Product("ThinkPad", "Lenovo", "Laptops", new BigDecimal(1500.00));
        Product product2 = new Product("Pixel", "Google", "Smartphones", new BigDecimal(800.00));

        productDAO.create(product1);
        productDAO.create(product2);

        Product fetchedProduct = productDAO.read(product1.getId());
        System.out.println("Fetched product: " + fetchedProduct);

        product1.setPrice(new BigDecimal(1400.00));
        productDAO.update(product1);

        System.out.println("All products:");
        productDAO.readAll().forEach(System.out::println);

        productDAO.delete(product2.getId());
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

    public static void performTransactionWithSavepoint() {
        String insertStatement = "INSERT INTO products (name, brand, category, price) VALUES (?, ?, ?, ?)";
        String insertStatementInvalid = "INSERT INTO products (name, brand, category price) VALUES (?, ?, ?, ?)";
        Random r = new Random();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(insertStatement);
                 PreparedStatement pstmt2 = conn.prepareStatement(insertStatementInvalid)) {

                pstmt1.setString(1, "Transactional Product 1");
                pstmt1.setString(2, brands[r.nextInt(brands.length)]);
                pstmt1.setString(3, categories[r.nextInt(categories.length)]);
                pstmt1.setBigDecimal(4, new BigDecimal(50.00));
                pstmt1.executeUpdate();
                System.out.println("First product added successfully");

                Savepoint savepoint = conn.setSavepoint("SavepointAfterFirstProduct");
                System.out.println("Savepoint created");

                pstmt2.setString(1, "Transactional Product 2");
                pstmt1.setString(2, brands[r.nextInt(brands.length)]);
                pstmt1.setString(3, categories[r.nextInt(categories.length)]);
                pstmt2.setBigDecimal(4, new BigDecimal(60.00));
                pstmt2.executeUpdate();
                System.out.println("Second product added successfully");

                conn.commit();
            } catch (SQLException e) {
                System.out.println("Error while performing transaction.");
                System.out.println(e.getMessage());
                conn.rollback();
                System.out.println("Rolled back to savepoint");
            }
        } catch (SQLException e) {
            System.out.println("Error while performing transaction");
            System.out.println(e.getMessage());
        }
    }
}
