package ShopApp.Db;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderDto {
    private int id;
    private int userId;
    private int state;
    private LocalDateTime lastTimeModified;
    private LocalDateTime timeCreated;
    private Map<Integer, Double> products = new HashMap<>();

    public OrderDto(int id, int userId, int state, LocalDateTime lastTimeModified, LocalDateTime timeCreated, Map<Integer, Double> products) {
        this.id = id;
        this.userId = userId;
        this.state = state;
        this.lastTimeModified = lastTimeModified;
        this.timeCreated = timeCreated;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getState() {
        return state;
    }

    public LocalDateTime getLastTimeModified() {
        return lastTimeModified;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public Map<Integer, Double> getProducts() {
        return products;
    }
}
