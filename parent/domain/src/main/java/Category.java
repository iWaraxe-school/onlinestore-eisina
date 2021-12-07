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

    public String printProducts() {
        String products = productList.toString();
        return products;
    }
}
