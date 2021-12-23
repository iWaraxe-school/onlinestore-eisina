import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.*;

import static org.reflections.scanners.Scanners.SubTypes;

public class StoreApp {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        StoreHelper helper = new StoreHelper();
        Store onlineStore = new Store();
        helper.populateStore(onlineStore);
        onlineStore.printAllCategoriesAndProduct();
    }
}

