import java.util.Map;

    class ThreadPurchasedOrder implements Runnable {
        Map<Product, Integer> finalMap;
        Integer time;
        Product product;

        public ThreadPurchasedOrder(Map<Product, Integer> finalMap) {
            this.finalMap = finalMap;
        }

        @Override
        public void run() {
            for (Map.Entry<Product, Integer> entry : finalMap.entrySet()) {
                product = entry.getKey();
                time = entry.getValue() * 1000;
            }
            try {
                Store.purchasedProducts.add(product);
                System.out.println(Thread.currentThread() + String.format(" Processed product - %s", product.getName()));
                System.out.println("Purchased products:");
                for (Product product : Store.purchasedProducts) {
                    System.out.print(product.getName() + " ");
                }
                Thread.sleep(time);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + "Error occurred.");
            }
        }
    }
