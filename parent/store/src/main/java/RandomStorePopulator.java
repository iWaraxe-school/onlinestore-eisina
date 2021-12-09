import com.github.javafaker.Faker;

public class RandomStorePopulator {
    private Faker faker = new Faker();

    public String setName(String categoryName){
        switch (categoryName){
            case "Food":
                return faker.food().fruit();
            case "Pet":
                return  faker.animal().name();
            default:
                return null;
        }
    }

    public int setRate(){
        return faker.random().nextInt(1,10);
    }

    public long setPrice(){
        return faker.random().nextLong();
    }
}
