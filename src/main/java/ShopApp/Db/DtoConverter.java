package ShopApp.Db;

import ShopApp.Model.IShopSetData;
import ShopApp.Model.Order;
import ShopApp.Model.ProductTools.ProductBuilder;
import ShopApp.Model.ProductTools.*;
import ShopApp.Model.User;
import ShopApp.Model.UserProduct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoConverter {

    public void convert(IShopDb db, IShopSetData shop) {
        Set<User> users = getUsers(db);
        Set<Productik> products = getProducts(db);
        Set<Order> orders = getOrders(db, users, products);
        Map<UserProduct, Double> ratings = getRatings(db, users, products);
        Set<String> keywords = getKeywords(db);

        shop.setUsers(users);
        shop.setProducts(products);
        shop.setOrders(orders);
        shop.setRatings(ratings);
        shop.setKeywords(keywords);
    }

    private Set<String> getKeywords(IShopDb db) {
        Set<KeywordDto> keywordDtos = db.getKeywords();
        Set<String> keywords = keywordDtos.stream().map(x -> x.getName()).collect(Collectors.toSet());
        return keywords;
    }

    private Map<UserProduct, Double> getRatings(IShopDb db, Set<User> users, Set<Productik> products) {
        Map<UserProduct, Double> ratings = new HashMap<>();
        Set<RatingDto> ratingsDtos = db.getRatings();
        Set<UserDto> userDtos = db.getUsers();
        Set<ProductDto> productDtos = db.getProducts();

        for (RatingDto dto : ratingsDtos){
            String login = userDtos.stream().filter(x -> x.getId() == dto.getUserId()).findFirst().get().getLogin();
            User user = users.stream().filter(x -> x.getLogin().equals(login)).findFirst().get();
            String productName = productDtos.stream().filter(x -> x.getId() == dto.getProductId()).findFirst().get().getName();
            Productik product = products.stream().filter(x -> x.getName().equals(productName)).findFirst().get();
            UserProduct up = new UserProduct(user, product);
            ratings.put(up, dto.getValue());
        }
        return ratings;
    }

    private Set<Order> getOrders(IShopDb db, Set<User> users, Set<Productik> products) {
        Set<OrderDto> orderDtos = db.getOrders();
        Set<UserDto> userDtos = db.getUsers();
        Set<ProductDto> productDtos = db.getProducts();
        Set<Order> orders = new HashSet<>();

        for (OrderDto dto : orderDtos){
            String login = userDtos.stream().filter(x -> x.getId() == dto.getUserId()).findFirst().get().getLogin();
            User user = users.stream().filter(x -> x.getLogin().equals(login)).findFirst().get();
            Map<Productik, Double> orderContent = new HashMap<>();
            for (int idProduct : dto.getProducts().keySet()){
                String productName = productDtos.stream().filter(x -> x.getId() == idProduct).findFirst().get().getName();
                Productik product = products.stream().filter(x -> x.getName().equals(productName)).findFirst().get();
                orderContent.put(product, dto.getProducts().get(idProduct));
            }

            Order order = null;
            for (Productik product : orderContent.keySet()){
                if (order == null){
                    order = new Order(dto.getId(), user, product, orderContent.get(product));
                }
                order.setProduct(product, orderContent.get(product));
            }
            orders.add(order);
        }
        return orders;
    }

    private Set<Productik> getProducts(IShopDb db) {
        Set<ProductDto> productDtos = db.getProducts();
        Set<KeywordDto> keywordDtos = db.getKeywords();
        Set<Productik> products = new HashSet<>();
        for (ProductDto dto : productDtos) {
            int dtoId = dto.getId();
            Set<String> keywords = keywordDtos.stream().filter(x -> x.getProductsId().contains(dtoId)).map(x -> x.getName()).collect(Collectors.toSet());
            Productik product = ProductBuilder.getInstance()
                    .setName(dto.getName()).setPrice(dto.getPrice()).setProducer(dto.getProducer()).addKeywords(keywords)
                    .build();
            products.add(product);
        }
        return products;
    }

    private Set<User> getUsers(IShopDb db) {
        Set<UserDto> dtos = db.getUsers();
        Set<User> users = new HashSet<>();
        for (UserDto dto : dtos) {
            User user = new User(dto.getLogin(), dto.getPassword(), dto.getName());
            users.add(user);
        }
        return users;
    }


}
