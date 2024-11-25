package lab5;

import java.text.DecimalFormat;

public class Product {
    private String title;
    private String manufacturer;
    private double price;

    public Product(String title, String manufacturer, double price) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return String.format("%s (%s) - %s₴", this.title, this.manufacturer, decimalFormat.format(this.price));
    }
}
