package ShopApp.Db;

import java.util.HashSet;
import java.util.Set;

public class ShopDbTest implements IShopDb{
    private Set<UserDto> users = new HashSet<>();
    private Set<ProductDto> products = new HashSet<>();
    private Set<OrderDto> orders = new HashSet<>();
    private Set<RatingDto> ratings = new HashSet<>();
    private Set<KeywordDto> keywords = new HashSet<>();


    public ShopDbTest Initialize(){
        (new ShopDbGenerator()).generate(this);
        return this;
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public Set<OrderDto> getOrders() {
        return orders;
    }

    public Set<RatingDto> getRatings() {
        return ratings;
    }

    public Set<KeywordDto> getKeywords() {
        return keywords;
    }
}
