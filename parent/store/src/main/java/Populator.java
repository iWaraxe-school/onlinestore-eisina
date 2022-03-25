import com.github.javafaker.Faker;

import java.sql.SQLException;

public class Populator {

    private IDatabaseHelper dbManager;
    private Faker faker = new Faker();

    public Populator(IDatabaseHelper dbManager) {
        this.dbManager = dbManager;
    }

    public void fillDbByFaker() throws SQLException {
        dbManager.createTableIfDoesNotExist();
        if (dbManager.getAllCategories().isEmpty()) {
            dbManager.insertCategoryIntoDB("Pet");
            dbManager.insertCategoryIntoDB("Food");
        }
        if (dbManager.getProductsForCategory("Pet").isEmpty()) {
            for (int i = 1; i < 10; i++) {
                dbManager.insertProductIntoDB(faker.food().fruit(), "Pet", faker.random().nextInt(1, 10000), faker.random().nextInt(1, 10));
            }
        }
        if (dbManager.getProductsForCategory("Food").isEmpty()) {
            for (int i = 10; i < 20; i++) {
                dbManager.insertProductIntoDB(faker.animal().name(), "Food", faker.random().nextInt(1, 10000), faker.random().nextInt(1, 10));
            }
        }
    }
}
