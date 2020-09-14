package ShopApp.Model;

import java.util.Map;
import java.util.Set;
import ShopApp.Model.ProductTools.*;

public interface IShopSetData {
    void setUsers(Set<User> users);
    void setProducts(Set<Productik> products);
    void setOrders(Set<Order> orders);
    void setKeywords(Set<String> keywords);
    void setRatings(Map<UserProduct, Double> ratings);
}
