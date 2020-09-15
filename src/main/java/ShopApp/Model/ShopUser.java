package ShopApp.Model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ShopUser {
    void save();

    User getUser(String login, String password);
    Set<Productik> getProducts();
    Set<String> getKeywords();
    Set<Order> getOrders();
    List<Order> getOrders(User user);
    Set<User> getUsers();
    Order getOrder(int id);
    void setRating(UserProduct userProduct, Double rating);
    Double getRating(User user, Productik product);
    Double getRating(UserProduct userProduct);
    Productik getProduct(String productName);
    void removeOrder(int id);
    User registerUser(String login, String password, String username);
    Map<Productik, Double> getRatings(User user);
    Order copy(int id);
    Order addOrder(User user);
}
