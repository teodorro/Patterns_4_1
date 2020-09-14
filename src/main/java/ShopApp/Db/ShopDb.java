package ShopApp.Db;

//public class ShopDb {
//    private Set<User> users = new HashSet<>();
//    private Set<Productik> products = new HashSet<>();
//    private Set<Order> orders = new HashSet<>();
//    private Map<UserProduct, Double> ratings = new HashMap<>();
//
//    public void addUser(User user) {
//        users.add(user);
//    }
//
//    public void addProduct(Productik product) {
//        products.add(product);
//    }
//
//    public void addProducts(Set<Productik> products) {
//        this.products.addAll(products);
//    }
//
//    public void addRating(User user, Productik product, Double rating) {
//        if (rating != null)
//            this.ratings.put(new UserProduct(user, product), rating);
//        else
//            this.ratings.remove(new UserProduct(user, product));
//    }
//
//    public Order addOrder(User user, Productik product, Double quantity){
//        Order order = new Order(user, product, quantity);
//        orders.add(order);
//        return order;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public Set<Productik> getProducts() {
//        return products;
//    }
//
//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public Map<UserProduct, Double> getRatings() {
//        return ratings;
//    }
//
//    public Double getProductRating(Productik product){
//        Set<UserProduct> filtered = getRatings().keySet().stream()
//                .filter(x -> x.getProduct() == product)
//                .collect(Collectors.toSet());
//        int count = filtered.size();
//        if (count == 0)
//            return null;
//        double sum = filtered.stream().map(y -> getRatings().get(y)).reduce(0d, (p, q) -> p + q);
//        return sum / count;
//    }
//
//    public Double getProductRating(User user, Productik product){
//        Set<UserProduct> filtered = getRatings().keySet().stream()
//                .filter(x -> x.getProduct() == product)
//                .collect(Collectors.toSet());
//        int count = filtered.size();
//        if (count == 0)
//            return null;
//        if (filtered.stream().anyMatch(x -> x.getUser() == user))
//            return filtered.stream().filter(x -> x.getUser() == user).findFirst().map(y -> getRatings().get(y)).get();
//        double sum = filtered.stream().map(y -> getRatings().get(y)).reduce(0d, (p, q) -> p + q);
//        return sum / count;
//    }
//}
