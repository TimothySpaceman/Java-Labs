package lab4;

import java.text.NumberFormat;
import java.util.*;

public class Cart {
    public List<CartItem> items;
    public Date date;

    public Cart(Date date) {
        this.items = new ArrayList<CartItem>();
        this.date = date;
    }

    public Cart add(CartItem item) {
        items.add(item);
        return this;
    }

    private String formatPrice(double price, Locale locale) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(price);
    }

    public String toFormattedString(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("data", locale);
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb, locale);

        int maxProductLength = bundle.getString("cartOutput.product").length();
        int maxCategoryLength = bundle.getString("cartOutput.category").length();
        int maxPriceLength = formatPrice(0, locale).length();
        int maxNumberLength = (int)Math.log10(items.size() * 10);

        items.forEach((item) -> {
        });

        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            total += item.price;
            if (item.productName.length() > maxProductLength) {
                maxProductLength = item.productName.length();
            }
            if (item.category.length() > maxCategoryLength) {
                maxCategoryLength = item.category.length();
            }
            if (formatPrice(item.price, locale).length() > maxPriceLength) {
                maxPriceLength = formatPrice(item.price, locale).length();
            }
        }

        int totalWidth = 3 + maxNumberLength*2 + maxProductLength + maxCategoryLength + maxPriceLength;

        f.format("%-" + (totalWidth - 20) + "s %2$td.%2$tm.%2$tY %2$tT\n", bundle.getString("cartOutput.dateOfBuying"), date);
        f.format("=".repeat(totalWidth) + "\n");

        f.format("№%" + maxNumberLength + "s %-"+ maxProductLength +"s %-"+ maxCategoryLength +"s %-" + maxPriceLength + "s\n", " ", bundle.getString("cartOutput.product"), bundle.getString("cartOutput.category"), bundle.getString("cartOutput.price"));
        f.format("=".repeat(totalWidth) + "\n");

        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            int numberLength = maxNumberLength - (int)Math.log10(i+1);
            f.format("%d.%" + numberLength + "s%-"+ maxProductLength +"s %-"+ maxCategoryLength +"s %s\n",
                    i + 1, " ", item.productName, item.category, formatPrice(item.price, locale));
        }

        f.format("=".repeat(totalWidth) + "\n");
        int totalLength = formatPrice(total, locale).length();
        f.format("%-" + (totalWidth - totalLength - 2) + "s %s", bundle.getString("cartOutput.total") + ":", formatPrice(total, locale));

        f.close();
        return sb.toString();
    }
}
