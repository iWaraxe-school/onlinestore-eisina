import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    Map<Category, Integer> categoryProductsMap;

    public void fillStore(Map<Category, Integer> categoryProductsMap) {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(randomStorePopulator.setName(entry.getKey().getName()), randomStorePopulator.setRate(), randomStorePopulator.setPrice());
                entry.getKey().addProduct(product);
            }
        }
        this.categoryProductsMap = categoryProductsMap;
    }

    public void printAllCategoriesAndProduct() {
        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            System.out.println(entry.getKey().toString() + " ");
        }
    }

    public List<Product> getProducts() {
        List<Product> resultik =  new ArrayList<>();
        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            resultik.addAll(entry.getKey().getProductList());
        }
        return resultik;
    }
}


