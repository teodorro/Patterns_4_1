package ShopApp;

import ShopApp.ProductTools.Productik;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShopDb {
    private Set<User> users = new HashSet<>();
    private Set<Productik> products = new HashSet<>();
    private Set<Order> orders = new HashSet<>();
    private Map<UserProduct, Integer> ratings = new HashMap<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void addProduct(Productik product) {
        products.add(product);
    }

    public void addProducts(Set<Productik> products) {
        this.products.addAll(products);
    }

    public void addRating(User user, Productik product, Integer rating) {
        if (rating != null)
            this.ratings.put(new UserProduct(user, product), rating);
        else
            this.ratings.remove(new UserProduct(user, product));
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Productik> getProducts() {
        return products;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Map<UserProduct, Integer> getRatings() {
        return ratings;
    }

    public Double getProductRating(Productik product){
        Set<UserProduct> filtered = getRatings().keySet().stream().filter(x -> x.getProduct() == product).collect(Collectors.toSet());
        int count = filtered.size();
        if (count == 0)
            return null;
        double sum = filtered.stream().map(y -> getRatings().get(y)).reduce(0, (p, q) -> p + q);
        return sum / count;
    }
}
