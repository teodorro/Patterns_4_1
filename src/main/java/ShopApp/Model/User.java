package ShopApp.Model;

import java.util.*;
import java.util.function.Supplier;
import ShopApp.Model.ProductTools.*;

public class User {
    private final String login;
    private String password;
    private String name;

    private Supplier<Map<Productik, Double>> ratingGetter;
    private List<Order> orders = new ArrayList<>();
    private List<Order> returnedOrders = new ArrayList<>();
    private Order currentOrder;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
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

    public Map<Productik, Double> getRatings() {
    return ratingGetter.get();
}

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getReturnedOrders() {
        return returnedOrders;
    }

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
