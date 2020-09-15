package ShopApp.Model;

import java.util.Map;
import java.util.Set;

public interface ShopSetData {
    void setUsers(Set<User> users);
    void setProducts(Set<Productik> products);
    void setOrders(Set<Order> orders);
    void setKeywords(Set<String> keywords);
    void setRatings(Map<UserProduct, Double> ratings);
}
