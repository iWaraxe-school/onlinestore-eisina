import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StoreHelper {

    public void populateStore(Store store) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections reflections = new Reflections();
        Map<Category, Integer> productsToAdd = new HashMap<>();
        Set<Class<? extends Category>> categories = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> category : categories) {
            productsToAdd.put(category.getConstructor().newInstance(), 5);
        }
        store.fillStore(productsToAdd);

    }
}
