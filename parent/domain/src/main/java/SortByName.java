import java.util.Comparator;

public class SortByName implements Comparator<Product> {

        public int compare(Product product1, Product product2) {

            String productName1 = product1.getName().toUpperCase();
            String productName2 = product2.getName().toUpperCase();

            return productName1.compareTo(productName2);
        }
    }


