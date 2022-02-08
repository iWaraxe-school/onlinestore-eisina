import com.github.javafaker.Faker;

public class RandomStorePopulatorPet implements Populator{
    private Faker faker = new Faker();

    @Override
    public String setName() {
        return  faker.animal().name();
    }

    @Override
    public int setRate() {
        return faker.random().nextInt(1,10);
    }

    @Override
    public int setPrice() {
        return faker.random().nextInt(1,1000);
    }
}
