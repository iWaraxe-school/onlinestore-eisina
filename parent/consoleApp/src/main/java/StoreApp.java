import org.xml.sax.SAXException;

import java.lang.reflect.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParserConfigurationException, SAXException, IOException, InterruptedException {


//        Please implement `create order` functionality. Each order should be processed in separate thread.
//        Whe user select product generate the random int from 1 to 30,
// and create thread that will process selected order for selected time, and after it
//        place the product in another collection (for example, purchased goods).
//        And create one more thread, that will be executed periodically, e.g. ones in 2 mins,
//        that will clean up purchased collection.

        StoreHelper helper = new StoreHelper();
        Store onlineStore = Store.getInstance();
        helper.populateStore(onlineStore);
        onlineStore.printAllCategoriesAndProduct();

        List<Product> productList = onlineStore.getProducts();
        SortHelper sortHelper = new SortHelper();
        Map<Product, Integer> finalmap;

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
                case "order":
                    System.out.println("Enter Product name");
                    String productName = input.next();
                    finalmap = ThreadTest.createOrder(productName,productList);
                    Thread t = new Thread(new ThreadTest.MessageLoop(finalmap));
                    new Thread(t).start();
                    break;
                case "quit":
                    flag = false;
                    return;
            }
            System.out.println();
        }
    }
}

