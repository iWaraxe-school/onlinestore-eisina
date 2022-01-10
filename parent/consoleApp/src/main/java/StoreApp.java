import org.reflections.Reflections;

import java.util.*;
import java.lang.reflect.*;

import static org.reflections.scanners.Scanners.SubTypes;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        StoreHelper helper = new StoreHelper();
        Store onlineStore = new Store();
        helper.populateStore(onlineStore);
    //    onlineStore.printAllCategoriesAndProduct();

        while (true)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter text: ");
            String option = input.next();
            switch (option){
                case "sort":
                    System.out.println("Soring the catalog");
                    break;
                case "top":
                    System.out.println("The top 5 products are:");
                    break;
                case "quit":
                    return;
            }
            System.out.println();
        }
    }
}

