import com.github.javafaker.Faker;

import java.util.*;

public class ThreadTest {

    static Faker faker = new Faker();

    static void threadMessage(String message) {
        String threadName =
                Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    static Map<Product, Integer> createOrder (String name, List<Product> productList){
        Map <Product, Integer> orderMap= new HashMap<>();
        for (Product product: productList){
            if (product.getName().equals(name)){
                Product resultt = product;
                orderMap.put(resultt,faker.random().nextInt(1,30));
            }
        }
        return orderMap;
    }

    public static class MessageLoop implements Runnable {
        Map<Product, Integer> finalMap;
        Integer time;
        Product product;
        List<Product> purchasedProduct = new ArrayList<Product>();
        public MessageLoop(Map<Product, Integer> finalMap) {
            this.finalMap = finalMap;
        }

        @Override
        public void run() {
            for (Map.Entry<Product, Integer> entry : finalMap.entrySet()) {
                product = entry.getKey();
                     time = entry.getValue()*1000;
                }
            try {
                    Thread.sleep(time);
                    purchasedProduct.add(product);
                    threadMessage(String.format("Processed %s", product.getName()));
                for (Product product: purchasedProduct) {
                    System.out.println("The following product is purchased: " + product.getName());
                }

            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }

    }
}
