public class StoreApp {
    public static void main(String[] args) {
        RandomStorePopulator random = new RandomStorePopulator();
        Category category = new Category();
        Product product = new Product(random.name, 3,29,category);
        System.out.println(product);

    }
}
