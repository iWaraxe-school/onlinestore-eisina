import org.apache.commons.lang3.builder.CompareToBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;

public class SortByAllParameters implements Comparator<Product> {
    private Map<String, String> sortConf;

    public SortByAllParameters(Map<String, String> sortConf) {
        this.sortConf = sortConf;
    }

    @Override
    public int compare(Product a, Product b) {
        CompareToBuilder compareBuilder = new CompareToBuilder();
        for (Map.Entry<String, String> item : sortConf.entrySet()) {
            String sortOrder = sortConf.get(item.getKey());
            try {
                if (sortOrder.equals(SortOrder.ASC.toString().toLowerCase()))
                    compareBuilder.append(this.getPropertyValue(a, item.getKey()).toUpperCase(), this.getPropertyValue(b, item.getKey()).toUpperCase()).toComparison();
                else
                    compareBuilder.append(this.getPropertyValue(b, item.getKey()).toUpperCase(), this.getPropertyValue(a, item.getKey()).toUpperCase());
            } catch (Exception e) {
                System.out.println("Error occured");
                return 0;
            }
        }
        return compareBuilder.toComparison();
    }

    private String getPropertyValue(Product product, String property) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = String.format("get%s",property.substring(0, 1).toUpperCase()+property.substring(1));
        Class<? extends Product> productClass = product.getClass();
        Method ProductMethod = productClass.getMethod(methodName);
        return String.valueOf(ProductMethod.invoke(product));
        }
}