package ShopApp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ShopApp.ProductTools.Productik;

public class Order {
    private List<Productik> products = new ArrayList<>();
    private User user;
    private OrderState state;
    private LocalDateTime lastTimeModified;
    private LocalDateTime timeCreated;

    public Order(User user, Productik product) {
        this.user = user;
        this.state = OrderState.CONSTRUCTING;
        this.products.add(product);
        lastTimeModified = LocalDateTime.now();
        timeCreated = LocalDateTime.now();
    }

    public Order setState(OrderState state){
        this.state = state;
        lastTimeModified = LocalDateTime.now();
        return this;
    }

    public Productik[] getProducts(){
        return (Productik[]) products.toArray();
    }

    public Order addProduct(Productik product){
        if (state != OrderState.CONSTRUCTING)
            throw new IllegalStateException("Добавление товара в заказ возможно только в режиме составления заказа");
        products.add(product);
        lastTimeModified = LocalDateTime.now();
        return this;
    }

    public void removeProduct(Productik product){
        if (state != OrderState.CONSTRUCTING)
            throw new IllegalStateException("Удаление товара из заказа возможно только в режиме составления заказа");
        if (products.contains(product)){
            products.remove(product);
            lastTimeModified = LocalDateTime.now();
        }
    }

    public LocalDateTime getLastTimeModified() {
        return lastTimeModified;
    }
}
