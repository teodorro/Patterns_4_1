package ShopApp;

import ShopApp.ProductTools.Productik;

import java.util.*;
import java.util.function.Supplier;

public class User {
    private int id;
    private final String login;
    private String password;
    private String name;
//    private Map<Productik, Double> ratings = new HashMap<>();
    private Supplier<Map<Productik, Double>> ratingGetter;
    private List<Order> orders = new ArrayList<>();
    private List<Order> returnedOrders = new ArrayList<>();
    private Order currentOrder;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        id = IdCreator.getInstance().getNextId();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

//    public Map<Productik, Double> getRatings() {
//        return ratings;
//    }
    public Map<Productik, Double> getRatings() {
    return ratingGetter.get();
}

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getReturnedOrders() {
        return returnedOrders;
    }

//    public void setRatings(Map<Productik, Double> ratings) {
//        this.ratings = ratings;
//    }
    public void setRatingGetter(Supplier<Map<Productik, Double>> ratingGetter){
        this.ratingGetter = ratingGetter;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    @Override
    public String toString() {
        return login;
    }
}
