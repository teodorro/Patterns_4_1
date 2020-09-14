package ShopApp.Db;

import java.util.Set;

public interface IShopDb {
    Set<UserDto> getUsers();
    Set<ProductDto> getProducts();
    Set<OrderDto> getOrders();
    Set<RatingDto> getRatings();
    Set<KeywordDto> getKeywords();
}
