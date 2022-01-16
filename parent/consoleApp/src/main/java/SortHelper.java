import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.tools.doclint.Entity.and;

public class SortHelper {

    HashMap<String, String> sortConf;

    public SortHelper() throws IOException, SAXException, ParserConfigurationException {
        ConfigHelper configHelper = new ConfigHelper();
        sortConf = configHelper.getSortConfig();
    }

    public void sort(List<Product> productList) throws IOException, SAXException, ParserConfigurationException {
        sort(productList, new SortByName(), "name");
        sort(productList, new SortByRate(), "rate");
        sort(productList, new SortByPrice(), "price");
        System.out.println(productList);
    }

    public void topProducts(List<Product> productList) throws IOException, SAXException, ParserConfigurationException {
        Collections.sort(productList, Collections.reverseOrder(new SortByPrice()));
        List<Product> firstFiveProducts = productList
                .stream()
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(firstFiveProducts);
    }

    private void sort(List<Product> productList, Comparator<Product> comparator, String property){
        String sortMode = sortConf.get(property);
        if (sortMode == null){
            return;
        }
        if (sortMode.equals("asc")) {
            Collections.sort(productList, comparator);
        }
        if (sortMode.equals("desc")) {
            Collections.sort(productList, Collections.reverseOrder(comparator));
        }
    }
}
