import com.github.javafaker.Faker;

public class RandomStorePopulator {
    Faker faker = new Faker();
    String name = faker.food().vegetable();
}
