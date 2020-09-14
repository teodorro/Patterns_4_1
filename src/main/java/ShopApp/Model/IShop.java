package ShopApp.Model;

import java.util.Set;
import ShopApp.Model.ProductTools.*;

public interface IShop {
    void save();

    User getUser(String login, String password);
    Set<Productik> getProducts();
    Set<String> getKeywords();
    Set<Order> getOrders();
    Set<Order> getOrders(User user);
    Set<User> getUsers();
    Order getOrder(int id);
    void setRating(UserProduct userProduct, Double rating);
    Double getRating(User user, Productik product);
    Double getRating(UserProduct userProduct);
    Productik getProduct(String productName);
    void removeOrder(int id);
    User registerUser(String login, String password, String username);
}
