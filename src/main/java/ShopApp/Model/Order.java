package ShopApp.Model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import ShopApp.Model.ProductTools.*;

public class Order implements Comparable<Order>{
    private int id;
    private Map<Productik, Double> products = new HashMap<>();
    private User user;
    private OrderState state;
    private LocalDateTime lastTimeModified;
    private LocalDateTime timeCreated;

    public Order(int id, User user, Productik product, Double quantity) {
        this.id = id;
        this.user = user;
        this.state = OrderState.CONSTRUCTING;
        this.products.put(product, quantity);
        lastTimeModified = LocalDateTime.now();
        timeCreated = LocalDateTime.now();
    }

    public Order setState(OrderState state){
        this.state = state;
        lastTimeModified = LocalDateTime.now();
        return this;
    }

    public Set<Productik> getProducts(){
        return products.keySet().stream().collect(Collectors.toSet());
    }

    public Order setProduct(Productik product, double number){
        if (state != OrderState.CONSTRUCTING)
            throw new IllegalStateException("Редактирование содержимого заказа возможно только на стадии составления заказа");
        products.put(product, number);
        lastTimeModified = LocalDateTime.now();
        return this;
    }

    public LocalDateTime getLastTimeModified() {
        return lastTimeModified;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public OrderState getState() {
        return state;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    @Override
    public int compareTo(Order order) {
        return Comparator.comparing(Order::getLastTimeModified)
                .thenComparing(Order::getId)
                .compare(this, order);
    }

}
