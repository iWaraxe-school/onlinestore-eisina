import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SortHelper {
    SortByAllParameters comparator;

    public SortHelper() throws IOException, SAXException, ParserConfigurationException {
        ConfigHelper configHelper = new ConfigHelper();
        Map<String, String> sortConf = configHelper.getSortConfig();
        comparator = new SortByAllParameters(sortConf);
    }

    public void sort(List<Product> productList) throws IOException, SAXException, ParserConfigurationException {
        Collections.sort(productList, comparator);
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void topProducts(List<Product> productList) throws IOException, SAXException, ParserConfigurationException {
        Collections.sort(productList, Collections.reverseOrder(new SortByPrice()));
        List<Product> firstFiveProducts = productList
                .stream()
                .limit(5)
                .collect(Collectors.toList());
        for (Product product : firstFiveProducts) {
            System.out.println(product);
        }
    }
}
