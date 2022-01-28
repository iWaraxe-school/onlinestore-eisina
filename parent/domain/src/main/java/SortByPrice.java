import java.util.Comparator;

public class SortByPrice implements Comparator<Product> {

    public int compare(Product product1, Product product2) {

        return product1.getPrice() - product2.getPrice();
    }
}