package ShopApp.Db;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class ShopDbNewGenerator {
    private IShopDb db;

    public IShopDb generate(IShopDb db) {
        this.db = db;

        addProducts();
        addUsers();
        addRatings();
        addOrders();
        addKeywords();

        return db;
    }

    private void addKeywords() {
        db.getKeywords().add(new KeywordDto("food",
                new HashSet<Integer>(){{
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId());
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Carrot")).findFirst().get().getId());
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Apple")).findFirst().get().getId());
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Potato")).findFirst().get().getId());
                }}));
        db.getKeywords().add(new KeywordDto("fruit",
                new HashSet<Integer>(){{
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId());
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Apple")).findFirst().get().getId());
                }}));
        db.getKeywords().add(new KeywordDto("vegetable",
                new HashSet<Integer>(){{
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Carrot")).findFirst().get().getId());
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Potato")).findFirst().get().getId());
                }}));
        db.getKeywords().add(new KeywordDto("house",
                new HashSet<Integer>(){{
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Toilet paper")).findFirst().get().getId());
                    add(db.getProducts().stream().filter(x -> x.getName().equals("Fairy")).findFirst().get().getId());
                }}));
    }

    private void addOrders() {
        db.getOrders().add(new OrderDto(IdCreator.getInstance().getNextId(),
                db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get().getId(),
                2, LocalDateTime.now(), LocalDateTime.now(),
                new HashMap<>(){{
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId(), 2d);
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Carrot")).findFirst().get().getId(), 2d);
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Apple")).findFirst().get().getId(), 2d);
                }}));
        db.getOrders().add(new OrderDto(IdCreator.getInstance().getNextId(),
                db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get().getId(),
                2, LocalDateTime.now(), LocalDateTime.now(),
                new HashMap<>(){{
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Carrot")).findFirst().get().getId(), 2d);
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId(), 2d);
                }}));
        db.getOrders().add(new OrderDto(IdCreator.getInstance().getNextId(),
                db.getUsers().stream().filter(x -> x.getLogin().equals("user3")).findFirst().get().getId(),
                2, LocalDateTime.now(), LocalDateTime.now(),
                new HashMap<>(){{
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Potato")).findFirst().get().getId(), 2d);
                    put(db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId(), 2d);
                }}));
    }

    private void addRatings() {
        db.getRatings().add(new RatingDto(
                db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get().getId(),
                db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId(),
                4d));
        db.getRatings().add(new RatingDto(
                db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get().getId(),
                db.getProducts().stream().filter(x -> x.getName().equals("Banana")).findFirst().get().getId(),
                5d));
        db.getRatings().add(new RatingDto(
                db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get().getId(),
                db.getProducts().stream().filter(x -> x.getName().equals("Carrot")).findFirst().get().getId(),
                5d));
        db.getRatings().add(new RatingDto(
                db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get().getId(),
                db.getProducts().stream().filter(x -> x.getName().equals("Potato")).findFirst().get().getId(),
                4d));
        db.getRatings().add(new RatingDto(
                db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get().getId(),
                db.getProducts().stream().filter(x -> x.getName().equals("Soap")).findFirst().get().getId(),
                3d));
    }

    private void addUsers() {
        db.getUsers().add(new UserDto(IdCreator.getInstance().getNextId(),
                "user1", "123", "alex"));
        db.getUsers().add(new UserDto(IdCreator.getInstance().getNextId(),
                "user2", "123", "tom"));
        db.getUsers().add(new UserDto(IdCreator.getInstance().getNextId(),
                "user3", "123", "matt"));
    }

    private void addProducts() {
        db.getProducts().add(new ProductDto(IdCreator.getInstance().getNextId(),
                "Banana", 100, "Equador", 3));
        db.getProducts().add(new ProductDto(IdCreator.getInstance().getNextId(),
                "Apple", 120, "Poland", 1));
        db.getProducts().add(new ProductDto(IdCreator.getInstance().getNextId(),
                "Carrot", 50, "Russia", 2));
        db.getProducts().add(new ProductDto(IdCreator.getInstance().getNextId(),
                "Potato", 30, "Belarus", 3));
        db.getProducts().add(new ProductDto(IdCreator.getInstance().getNextId(),
                "Toilet paper", 10, null, 0));
        db.getProducts().add(new ProductDto(IdCreator.getInstance().getNextId(),
                "Soap", 100, "Fairy", 1));
    }
}
