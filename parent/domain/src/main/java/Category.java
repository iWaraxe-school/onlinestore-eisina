import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private String name;
    List<Product> productList;

    public Category(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public String toString() {
        String products = productList.toString();
        return products;
    }

    public String getName() {
        return name;
    }
}

