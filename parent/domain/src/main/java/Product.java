import java.util.Comparator;

public class Product {
    private String name;
    private int rate;
    private int price;

    public Product(String name, int rate, int price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        String productInfo = " Name: " + name + " Rate: " + rate + " Price: " + price;
        return productInfo;
    }
}

