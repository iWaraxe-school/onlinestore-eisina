import java.sql.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    Map<Category, Integer> categoryProductsMap;

    public void fillStore(Map<Category, Integer> categoryProductsMap) {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(randomStorePopulator.setName("Food"), randomStorePopulator.setRate(), randomStorePopulator.setPrice());
                entry.getKey().addProduct(product);
            }
        }
        this.categoryProductsMap = categoryProductsMap;
    }

        public void printAllCategoriesAndProduct () {
            for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
                System.out.print(entry.getKey().toString());
            }
    }
}

