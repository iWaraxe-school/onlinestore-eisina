
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.*;

public class Store {
    Map<Category, Integer> categoryProductsMap;
    public static List<Product> purchasedProducts = new ArrayList<>();
    static Faker faker = new Faker();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    private static Store instance;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void printAllCategoriesAndProduct() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        rs = readProduct();
        printProductInfo(rs);
    }

    public List<Product> getProducts() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        rs = readProduct();
        List<Product> listOfAllProducts = new ArrayList<>();
        while (rs.next()) {
            Product listed = new Product(rs.getString("Name"), rs.getInt("rate"), rs.getInt("price"));
            listOfAllProducts.add(listed);
        }
        return listOfAllProducts;
    }

    public Product getProductByName(String productName) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Optional<Product> orderedProduct = getProducts().stream()
                .filter(x -> x.getName().equals(productName))
                .findFirst();
        Product product = orderedProduct.isPresent() ? orderedProduct.get() : null;
        return product;
    }

    static void createOrder(String name) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Map<Product, Integer> orderMap = new HashMap<>();
        orderMap.put(instance.getProductByName(name), faker.random().nextInt(1, 30));
        Thread t = new Thread(new ThreadPurchasedOrder(orderMap));
        new Thread(t).start();
    }

    public void printProductInfo(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int rate = rs.getInt("rate");
            String category = rs.getString("category");
            System.out.println(" Category - " + category + " Product " + name + " Rate - " + rate + " Price " + price);
        }
    }

    public ResultSet readProduct() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        con = DatabaseHelper.getConnection();
        stmt = con.createStatement();
        String sql = "SELECT * FROM PRODUCTS";
        return stmt.executeQuery(sql);
    }
}


