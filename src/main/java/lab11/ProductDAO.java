package lab11;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {
    public static Connection conn = null;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO products (name, brand, category, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getBrand());
            pstmt.setString(3, product.getCategory());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
            }

            System.out.println("Product created: " + product);
        } catch (SQLException e) {
            System.out.println("Error while creating product");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Product read(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Product product = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("brand"),
                            rs.getString("category"),
                            rs.getBigDecimal("price")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while reading product");
            System.out.println(e.getMessage());
        }

        return product;
    }

    @Override
    public List<Product> readAll() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error while reading products");
            System.out.println(e.getMessage());
        }

        return products;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, brand = ?, category = ?, price = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getBrand());
            pstmt.setString(3, product.getCategory());
            pstmt.setBigDecimal(4, product.getPrice());
            pstmt.setInt(5, product.getId());
            pstmt.executeUpdate();

            System.out.println("Product updated: " + product);
        } catch (SQLException e) {
            System.out.println("Error while updating product");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Product with ID " + id + " deleted");
        } catch (SQLException e) {
            System.out.println("Error while deleting product");
            System.out.println(e.getMessage());
        }
    }
}