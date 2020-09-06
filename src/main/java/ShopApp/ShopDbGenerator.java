package ShopApp;

import ShopApp.ProductTools.ProductBuilder;
import ShopApp.ProductTools.Productik;

public class ShopDbGenerator {
    private ShopDb db;

    public ShopDb generate(){
        db = new ShopDb();

        addProducts();
        addUsers();
        addRatings();
        addOrders();

        return db;
    }

    private void addRatings() {
        User user1 = db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get();
        User user2 = db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get();
        Productik banana = getProduct("Banana");
        Productik carrot = getProduct("Carrot");
        Productik potato = getProduct("Potato");
        Productik soap = getProduct("Soap");
        db.addRating(user1, banana, 4);
        db.addRating(user2, banana, 5);
        db.addRating(user1, carrot, 5);
        db.addRating(user2, potato, 4);
        db.addRating(user1, soap, 3);
    }

    private Productik getProduct(String name){
        return db.getProducts().stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }

    private void addOrders() {
        User user1 = db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get();
        User user2 = db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get();
        User user3 = db.getUsers().stream().filter(x -> x.getLogin().equals("user3")).findFirst().get();
        Productik banana = getProduct("Banana");
        Productik carrot = getProduct("Carrot");
        Productik potato = getProduct("Potato");
        Productik soap = getProduct("Soap");
        Productik apple = getProduct("Apple");
        Order order1 = db.addOrder(user1, banana);
        order1.addProduct(carrot);
        order1.addProduct(apple);
        Order order2 = db.addOrder(user2, banana);
        order2.addProduct(carrot);
        Order order3 = db.addOrder(user3, banana);
        order3.addProduct(potato);
        db.addOrder(user1, potato);
        db.addOrder(user2, potato);
        db.addOrder(user1, soap);
        for (Order order : db.getOrders()){
            order.setState(OrderState.DELIVERED);
        }
    }

    private void addUsers() {
        db.addUser(new User("user1", "123", "alex"));
        db.addUser(new User("user2", "123", "tom"));
        db.addUser(new User("user3", "123", "matt"));
    }

    private void addProducts() {
        ProductBuilder.getInstance().setRatingGetter(x -> db.getProductRating(x));

        Productik product = ProductBuilder.getInstance()
                .setName("Banana").setPrice(100).setProducer("Equador").addKeyword("food").addKeyword("fruit")
                .build();
        product.addSell();
        product.addSell();
        product.addSell();
        db.addProduct(product);

        product = ProductBuilder.getInstance()
                .setName("Apple").setPrice(120).setProducer("Poland").addKeyword("food").addKeyword("fruit")
                .build();
        product.addSell();
        db.addProduct(product);

        product = ProductBuilder.getInstance()
                .setName("Carrot").setPrice(50).setProducer("Russia").addKeyword("food").addKeyword("vegetable")
                .build();
        product.addSell();
        product.addSell();
        db.addProduct(product);

        product = ProductBuilder.getInstance()
                .setName("Potato").setPrice(30).setProducer("Belarus").addKeyword("food").addKeyword("vegetable")
                .build();
        product.addSell();
        product.addSell();
        product.addSell();
        db.addProduct(product);

        product = ProductBuilder.getInstance()
                .setName("Toilet paper").setPrice(10).addKeyword("house")
                .build();
        db.addProduct(product);

        product = ProductBuilder.getInstance()
                .setName("Soap").setPrice(100).setProducer("Fairy").addKeyword("house")
                .build();
        product.addSell();
        db.addProduct(product);
    }
}
