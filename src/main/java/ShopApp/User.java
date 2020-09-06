package ShopApp;

import ShopApp.ProductTools.Productik;

import java.util.*;
import java.util.function.Function;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private Map<Productik, Double> ratings = new HashMap<>();
//    private Function<User, Double> ratingGetter;
    private List<Order> orders = new ArrayList<>();
    private List<Order> returnedOrders = new ArrayList<>();

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

    public Map<Productik, Double> getRatings() {
        return ratings;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getReturnedOrders() {
        return returnedOrders;
    }
}
