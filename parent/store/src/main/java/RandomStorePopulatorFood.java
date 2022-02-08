import com.github.javafaker.Faker;

public class RandomStorePopulatorFood implements Populator{
    private Faker faker = new Faker();

    public String setName(){
        return faker.food().fruit();
    }

    public int setRate(){
        return faker.random().nextInt(1,10);
    }

    public int setPrice(){
        return faker.random().nextInt(1,10000);
    }
}
