public class Product {
    private String name;
    private int rate;
    private int price;
    private Category category;

    public Product(String name, int rate, int price, Category category) {
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.category = category;
    }
}
