import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDatabaseHelper {

        Connection getConnection();

        void dispose() throws SQLException;

        void createTableIfDoesNotExist() throws SQLException;

        void insertCategoryIntoDB(String categoryName) throws SQLException;

        JSONObject insertCategoryIntoDB(JSONObject categoryName) throws SQLException;


        void insertProductIntoDB(String name, String category, int price, int rate) throws SQLException;

        List<Category> getAllCategories() throws SQLException;

        List<Product> getProductsForCategory(String category) throws SQLException;

}
