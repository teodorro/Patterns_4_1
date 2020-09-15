package ShopApp.Model;

import ShopApp.Db.IdCreator;

import java.util.*;
import java.util.stream.Collectors;

public class Shop implements ShopUser, ShopSetData {
    public final int MAX_RATING = 5;
    public final int MIN_RATING = 1;

    private Set<User> users;
    private Set<Productik> products;
    private Set<Order> orders;
    private Map<UserProduct, Double> ratings;
    private Set<String> keywords;


    @Override
    public void save() {

    }

    //region SetData

    @Override
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public void setProducts(Set<Productik> products) {
        this.products = products;
    }

    @Override
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void setRatings(Map<UserProduct, Double> ratings) {
        this.ratings = ratings;
    }

    //endregion


    @Override
    public Set<Productik> getProducts() {
        return products;
    }

    @Override
    public Set<String> getKeywords() {
        return keywords;
    }

    @Override
    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public Set<Order> getOrders(User user) {
        return orders.stream().anyMatch(x -> x.getUser() == user)
                ? orders.stream().filter(x -> x.getUser() == user)
                    .collect(Collectors.toSet())
//                    .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.naturalOrder())))
                : new TreeSet<>();
    }

    @Override
    public Set<User> getUsers() {
        return users;
    }

    @Override
    public Order getOrder(int id) {
        return orders.stream().anyMatch(x -> x.getId() == id)
                ? orders.stream().filter(x -> x.getId() == id).findFirst().get()
                : null;
    }

    @Override
    public User getUser(String login, String password) {
        return users.stream().anyMatch(x -> x.getLogin().equals(login) && x.getPassword().equals(password))
                ? users.stream().filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password)).findFirst().get()
                : null;
    }

    @Override
    public void setRating(UserProduct userProduct, Double rating) {
        if (rating != null)
            ratings.put(userProduct, rating);
        else
            ratings.remove(userProduct);
    }

    @Override
    public Double getRating(User user, Productik product) {
        return ratings.keySet().stream().anyMatch(x -> x.getUser() == user && x.getProduct() == product)
                ? ratings.get(ratings.keySet().stream().filter(x -> x.getUser() == user && x.getProduct() == product).findFirst().get())
                : null;
    }

    @Override
    public Double getRating(UserProduct userProduct) {
        return ratings.keySet().stream().anyMatch(x -> x.equals(userProduct))
                ? ratings.get(ratings.keySet().stream().filter(x -> x.equals(userProduct)).findFirst().get())
                : null;
    }

    @Override
    public Double getAvgRating(Productik product) {
        return ratings.keySet().stream().filter(x -> x.getProduct() == product).count() > 0
                ? ratings.keySet().stream().filter(x -> x.getProduct() == product).map(y -> ratings.get(y)).reduce((x, y) -> x + y).get()
                / ratings.keySet().stream().filter(x -> x.getProduct() == product).count()
                : null;
    }

//    @Override
//    public Double getRatingSetByUser(UserProduct userProduct) {
//        return ratings.keySet().stream().anyMatch(x -> x.equals(userProduct))
//                ? ratings.get(ratings.keySet().stream().filter(x -> x.equals(userProduct)).findFirst().get())
//                : null;
//    }

    @Override
    public Productik getProduct(String productName) {
        return products.stream().anyMatch(x -> x.getName().equals(productName))
                ? products.stream().filter(x -> x.getName().equals(productName)).findFirst().get()
                : null;
    }

    @Override
    public void removeOrder(int id) {
        Order order = orders.stream().anyMatch(x -> x.getId() == id)
                ? orders.stream().filter(x -> x.getId() == id).findFirst().get()
                : null;
        if (order != null)
            orders.remove(order);
    }

    @Override
    public User registerUser(String login, String password, String username) {
        if (users.stream().anyMatch(x -> x.getLogin().equals(login)))
            throw new IllegalArgumentException("Login already exists");
        User user = UserBuilder.getInstance().setLogin(login).setPassword(password).setUsername(username).build();
        users.add(user);
        return user;
    }

    @Override
    public Map<Productik, Double> getRatings(User user) {
        if (!ratings.keySet().stream().anyMatch(x -> x.getUser() == user))
            return new HashMap<>();
        Map<Productik, Double> userRatings = ratings.keySet().stream().filter(x -> x.getUser() == user)
                .collect(Collectors.toMap(x -> x.getProduct(), x -> ratings.get(x)));
        return userRatings;
    }

    @Override
    public Order copy(int id) {
        if (orders.stream().noneMatch(x -> x.getId() == id))
            return null;
        Order orderOriginal = orders.stream().filter(x -> x.getId() == id).findFirst().get();
        Order order = new Order(IdCreator.getInstance().getNextId(), orderOriginal.getUser());
        for (Productik product : orderOriginal.getProducts()){
            order.setProduct(product, orderOriginal.getProductAmounts().get(product));
        }
        return order;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }


}
