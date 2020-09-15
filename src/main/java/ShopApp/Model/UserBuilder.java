package ShopApp.Model;


public class UserBuilder {
    private String username;
    private String login;
    private String password;


    private static UserBuilder instance;
    public static UserBuilder getInstance(){
        if (shop == null)
            throw new NullPointerException("Initialize shop first");
        if (instance == null)
            instance = new UserBuilder();
        return instance;
    }

    private static ShopUser shop;
    public static void setShop(ShopUser shopchik){
        shop = shopchik;
    }

    private UserBuilder() {
    }

    public UserBuilder setUsername(String username){
        this.username = username;
        return this;
    }

    public UserBuilder setLogin(String login){
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public User build(){
        if (username == null)
            throw new NullPointerException("Empty username");
        if (login == null)
            throw new NullPointerException("Empty login");
        if (password == null)
            throw new NullPointerException("Empty password");
        User user = new User(login, password, username);
        user.setOrderAdder(x -> shop.getOrders().add(x));
        user.setOrderGetter(() -> shop.getOrders(user));
        user.setRatingGetter(() -> shop.getRatings(user));
        return user;
    }




}
