package ShopApp.Model;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class User {
    private final String login;
    private String password;
    private String name;

    private Supplier<Map<Productik, Double>> ratingGetter;
    private Supplier<List<Order>> orderGetter;
    private Consumer<Order> orderAdder;
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
        return orderGetter.get();
    }

    public void addOrder(Order order){
        orderAdder.accept(order);
    }

    public void setRatingGetter(Supplier<Map<Productik, Double>> ratingGetter){
        this.ratingGetter = ratingGetter;
    }

    public void setOrderGetter(Supplier<List<Order>> orderGetter){
        this.orderGetter = orderGetter;
    }

    public void setOrderAdder(Consumer<Order> orderAdder){
        this.orderAdder = orderAdder;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setStartCurrentOrder(){
        currentOrder.setState(OrderState.PREPARING);

    }

    @Override
    public String toString() {
        return login;
    }
}
