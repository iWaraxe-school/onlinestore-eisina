import java.util.Comparator;

public class SortByRate implements Comparator<Product> {

    public int compare(Product product1, Product product2) {

        return product1.getRate() - product2.getRate();
    }
}
