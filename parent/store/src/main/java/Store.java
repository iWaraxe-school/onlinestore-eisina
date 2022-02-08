
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    Map<Category, Integer> categoryProductsMap;
    private static Store instance;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void fillStore(Map<Category, Integer> categoryProductsMap) {
        RandomStorePopulatorFood randomStorePopulatorFood = new RandomStorePopulatorFood();
        RandomStorePopulatorPet randomStorePopulatorPet = new RandomStorePopulatorPet();

        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                switch (entry.getKey().getName()) {
                    case "Food":
                        Product foodProduct = new Product(randomStorePopulatorFood.setName(), randomStorePopulatorFood.setRate(), randomStorePopulatorFood.setPrice());
                        entry.getKey().addProduct(foodProduct);
                        break;
                    case "Pet":
                        Product petProduct = new Product(randomStorePopulatorPet.setName(), randomStorePopulatorPet.setRate(), randomStorePopulatorPet.setPrice());
                        entry.getKey().addProduct(petProduct);
                        break;
                }
            }
            this.categoryProductsMap = categoryProductsMap;
        }
    }

    public void printAllCategoriesAndProduct() {
        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            System.out.println(entry.getKey().toString() + " ");
        }
    }

    public List<Product> getProducts() {
        List<Product> listOfAllProducts =  new ArrayList<>();
        for (Map.Entry<Category, Integer> entry : categoryProductsMap.entrySet()) {
            listOfAllProducts.addAll(entry.getKey().getProductList());
        }
        return listOfAllProducts;
    }
}


