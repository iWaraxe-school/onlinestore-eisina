import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.*;

public class StoreApp {
    public static void main(String[] args) {

        Store onlineStore = new Store();
        Class <?> store = onlineStore.getClass();
        Field[] fields = store.getDeclaredFields();

        Map<Category, Integer> productsToAdd = new HashMap<>();
        productsToAdd.put(new FoodCategory(), 5);
        productsToAdd.put(new PetCategory(), 6);

        onlineStore.fillStore(productsToAdd);
        onlineStore.printAllCategoriesAndProduct();
    }
}

