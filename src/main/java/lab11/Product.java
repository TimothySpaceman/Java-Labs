package lab11;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private String brand;
    private String category;
    private BigDecimal price;

    public Product() {}

    public Product(String name, String brand, String category, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public Product(int id, String name, String brand, String category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name + " by " + brand + ", " + price + " [" + category + "]";
    }
}