package ShopApp.Model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Order implements Comparable<Order>{
    private int id;
    private Map<Productik, Double> products = new HashMap<>();
    private User user;
    private OrderState state;
    private LocalDateTime lastTimeModified;
    private LocalDateTime timeCreated;


    public Order(int id, User user) {
        this.id = id;
        this.user = user;
        this.state = OrderState.CONSTRUCTING;
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

    public Map<Productik, Double> getProductAmounts(){
        return products;
    }

    public Order setProduct(Productik product, double number){
        if (state != OrderState.CONSTRUCTING)
            throw new IllegalStateException("Editing order is possible only at constructing state");
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
