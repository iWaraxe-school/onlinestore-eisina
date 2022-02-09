import org.xml.sax.SAXException;

import java.lang.reflect.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, IOException {

        StoreHelper helper = new StoreHelper();
        Store onlineStore = Store.getInstance();
        helper.populateStore(onlineStore);
        onlineStore.printAllCategoriesAndProduct();

        List<Product> productList = onlineStore.getProducts();
        SortHelper sortHelper = new SortHelper();

        Boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter text: ");
            String option = input.next();
            switch (option){
                case "sort":
                    System.out.println("Sorting the catalog:");
                    sortHelper.sort(productList);
                    break;
                case "top":
                    System.out.println("The top 5 products are:");
                    sortHelper.topProducts(productList);
                    break;
                case "quit":
                    flag = false;
                    return;
            }
            System.out.println();
        }
    }
}

