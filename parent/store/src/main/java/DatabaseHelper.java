import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper implements IDatabaseHelper {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/testKate";
    private static final String USER = "sa";
    private static final String PASS = "";
    private Connection conn;
    private Statement stmt;

    public Connection getConnection() {
        try {
            Class.forName(DatabaseHelper.JDBC_DRIVER).newInstance();
            try {
                conn = DriverManager.getConnection(DatabaseHelper.DB_URL, DatabaseHelper.USER, DatabaseHelper.PASS);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void dispose() throws SQLException {
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }

    @Override
    public void createTableIfDoesNotExist() throws SQLException {
        conn = getConnection();
        stmt = conn.createStatement();
        String sqlCategories =
                "CREATE TABLE IF NOT EXISTS CATEGORIES (CAT_ID integer NOT NULL AUTO_INCREMENT, "
                        + "CATEGORY_NAME varchar(40) NOT NULL, "
                        + "PRIMARY KEY (CAT_ID))";
        stmt.executeUpdate(sqlCategories);
        String sqlProducts = "CREATE TABLE IF NOT EXISTS PRODUCTS " +
                "(PROD_ID INTEGER not NULL AUTO_INCREMENT, " +
                " NAME VARCHAR(255), " +
                " RATE INTEGER," +
                " PRICE INTEGER, " +
                " CAT_ID INTEGER, " +
                " PRIMARY KEY ( PROD_ID )," +
                "FOREIGN KEY (CAT_ID) REFERENCES CATEGORIES (CAT_ID))";
        stmt.executeUpdate(sqlProducts);
    }

    @Override
    public void insertCategoryIntoDB(String categoryName) throws SQLException {
        conn = getConnection();
        stmt = conn.createStatement();
        String SQL_INSERT = "INSERT INTO CATEGORIES (CATEGORY_NAME) VALUES (?)";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
        preparedStatement.setString(1, categoryName);
        preparedStatement.executeUpdate();
    }

    @Override
    public void insertProductIntoDB(String name, String category, int price, int rate) throws SQLException {
        conn = getConnection();
        stmt = conn.createStatement();
        String sql = String.format("SELECT CAT_ID FROM CATEGORIES WHERE CATEGORY_NAME = '%s'", category);
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int categoryID = rs.getInt("CAT_ID");
        String SQL_INSERT = "INSERT INTO PRODUCTS (NAME, RATE, PRICE, CAT_ID) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, rate);
        preparedStatement.setInt(3, price);
        preparedStatement.setInt(4, categoryID);
        preparedStatement.executeUpdate();
    }


    @Override
    public List<Category> getAllCategories() throws SQLException {
        conn = getConnection();
        stmt = conn.createStatement();
        String sql = "SELECT * FROM CATEGORIES";
        ResultSet rs = stmt.executeQuery(sql);
        List<Category> listOfAllCategories = new ArrayList<>();
        while (rs.next()) {
            Category listed = new Category(rs.getString("CATEGORY_NAME"));
            listOfAllCategories.add(listed);
        }
        return listOfAllCategories;

    }

    @Override
    public List<Product> getProductsForCategory(String category) throws SQLException {
        conn = getConnection();
        stmt = conn.createStatement();
        String sql = String.format("SELECT P.NAME, P.PRICE, P.RATE, C.CATEGORY_NAME " +
                "               FROM PRODUCTS P " +
                "                INNER JOIN CATEGORIES C " +
                "                ON C.CAT_ID=P.CAT_ID " +
                "WHERE C.CATEGORY_NAME = '%s'", category);
        ResultSet rs = stmt.executeQuery(sql);
        List<Product> listOfAllProducts = new ArrayList<>();
        while (rs.next()) {
            Product listed = new Product(rs.getString("Name"), rs.getInt("rate"), rs.getInt("price"));
            listOfAllProducts.add(listed);
        }
        return listOfAllProducts;
    }
}
