package ShopApp.Db;

//public class ShopDbGenerator {
//    private ShopDb db;
//
//    public ShopDb generate(){
//        db = new ShopDb();
//
//        addProducts();
//        addUsers();
//        addRatings();
//        addOrders();
//
//        return db;
//    }
//
//    private void addRatings() {
//        User user1 = db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get();
//        User user2 = db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get();
//        Productik banana = getProduct("Banana");
//        Productik carrot = getProduct("Carrot");
//        Productik potato = getProduct("Potato");
//        Productik soap = getProduct("Soap");
//        db.addRating(user1, banana, 4d);
//        db.addRating(user2, banana, 5d);
//        db.addRating(user1, carrot, 5d);
//        db.addRating(user2, potato, 4d);
//        db.addRating(user1, soap, 3d);
//    }
//
//    private Productik getProduct(String name){
//        return db.getProducts().stream().filter(x -> x.getName().equals(name)).findFirst().get();
//    }
//
//    private void addOrders() {
//        User user1 = db.getUsers().stream().filter(x -> x.getLogin().equals("user1")).findFirst().get();
//        User user2 = db.getUsers().stream().filter(x -> x.getLogin().equals("user2")).findFirst().get();
//        User user3 = db.getUsers().stream().filter(x -> x.getLogin().equals("user3")).findFirst().get();
//        Productik banana = getProduct("Banana");
//        Productik carrot = getProduct("Carrot");
//        Productik potato = getProduct("Potato");
//        Productik soap = getProduct("Soap");
//        Productik apple = getProduct("Apple");
//        Order order1 = db.addOrder(user1, banana, 2d);
//        order1.setProduct(carrot, 2d);
//        order1.setProduct(apple, 2d);
//        Order order2 = db.addOrder(user2, banana, 2d);
//        order2.setProduct(carrot, 2d);
//        Order order3 = db.addOrder(user3, banana, 2d);
//        order3.setProduct(potato, 2d);
//        db.addOrder(user1, potato, 2d);
//        db.addOrder(user2, potato, 2d);
//        db.addOrder(user1, soap, 2d);
//        for (Order order : db.getOrders()){
//            order.setState(OrderState.DELIVERED);
//        }
//    }
//
//    private void addUsers() {
//        User user1 = new User("user1", "123", "alex");
//        db.addUser(user1);
//        user1.setRatingGetter(() -> getUsersRatings(user1));
////        user1.setRatingGetter(() -> db.getRatings()
////                .keySet().stream().filter(x -> x.getUser() == user1)
////                .map(up -> up.getProduct()));
//        User user2 = new User("user2", "123", "tom");
//        db.addUser(user2);
//        user2.setRatingGetter(() -> getUsersRatings(user2));
//        User user3 = new User("user3", "123", "matt");
//        db.addUser(user3);
//        user3.setRatingGetter(() -> getUsersRatings(user3));
//    }
//
//    private Map<Productik, Double> getUsersRatings(User user) {
//        return db.getRatings().keySet().stream().filter(x -> x.getUser() == user)
//                .collect(Collectors.toMap(
//                        UserProduct::getProduct,
//                        x -> db.getRatings().get(x)));
//    }
//
//    private void addProducts() {
//        ProductBuilder.getInstance().setRatingGetter(x -> db.getProductRating(x));
////        ProductBuilder.getInstance().setRatingGetter2(x -> db.getProductRating(x));
//
//        Productik product = ProductBuilder.getInstance()
//                .setName("Banana").setPrice(100).setProducer("Equador").addKeyword("food").addKeyword("fruit")
//                .build();
//        product.addSell();
//        product.addSell();
//        product.addSell();
//        db.addProduct(product);
//
//        product = ProductBuilder.getInstance()
//                .setName("Apple").setPrice(120).setProducer("Poland").addKeyword("food").addKeyword("fruit")
//                .build();
//        product.addSell();
//        db.addProduct(product);
//
//        product = ProductBuilder.getInstance()
//                .setName("Carrot").setPrice(50).setProducer("Russia").addKeyword("food").addKeyword("vegetable")
//                .build();
//        product.addSell();
//        product.addSell();
//        db.addProduct(product);
//
//        product = ProductBuilder.getInstance()
//                .setName("Potato").setPrice(30).setProducer("Belarus").addKeyword("food").addKeyword("vegetable")
//                .build();
//        product.addSell();
//        product.addSell();
//        product.addSell();
//        db.addProduct(product);
//
//        product = ProductBuilder.getInstance()
//                .setName("Toilet paper").setPrice(10).addKeyword("house")
//                .build();
//        db.addProduct(product);
//
//        product = ProductBuilder.getInstance()
//                .setName("Soap").setPrice(100).setProducer("Fairy").addKeyword("house")
//                .build();
//        product.addSell();
//        db.addProduct(product);
//    }
//}
