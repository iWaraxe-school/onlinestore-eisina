import java.util.Comparator;
import java.util.Map;

public class SortByAllParameters implements Comparator<Product> {
    private SortOrder nameOrder = SortOrder.ASC;
    private SortOrder priceOrder = SortOrder.ASC;
    private SortOrder rateOrder = SortOrder.ASC;

    public SortByAllParameters(Map<String, String> sortConf) {
        if (sortConf == null) {
            return;
        }
        if (sortConf.containsKey("name") && sortConf.get("name").equals(SortOrder.DESC.toString().toLowerCase())) {
            nameOrder = SortOrder.DESC;
        }
        if (sortConf.containsKey("price") && sortConf.get("price").equals(SortOrder.DESC.toString().toLowerCase())) {
            priceOrder = SortOrder.DESC;
        }
        if (sortConf.containsKey("rate") && sortConf.get("rate").equals(SortOrder.DESC.toString().toLowerCase())) {
            rateOrder = SortOrder.DESC;
        }
    }

    @Override
    public int compare(Product p1, Product p2) {
        int c;
        if (nameOrder == SortOrder.ASC) {
            c = p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
        } else {
            c = p2.getName().toUpperCase().compareTo(p1.getName().toUpperCase());
        }
        if (c == 0) {
            if (priceOrder == SortOrder.ASC) {
                c = p1.getPrice() - p2.getPrice();
            } else {
                c = p2.getPrice() - p1.getPrice();
            }
        }
        if (c == 0) {
            if (rateOrder == SortOrder.ASC) {
                c = p1.getRate() - p2.getRate();
            } else {
                c = p2.getRate() - p1.getRate();
            }
        }
        return c;
    }
}