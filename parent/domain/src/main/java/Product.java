public class Product {
    private String name;
    private int rate;
    private long price;

    public Product(String name, int rate, long price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String printProduct(){
        String productInfo = "Name: " + name + " Rate: " + rate + "Price: " + price;
        return productInfo;
    }
}
