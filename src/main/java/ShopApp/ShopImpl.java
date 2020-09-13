package ShopApp;

import ShopApp.ProductTools.Productik;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShopImpl extends InputProcessor {

    public final int MAX_RATING = 5;
    public final int MIN_RATING = 1;
    private ShopDb db;
    private Set<String> keywords = new HashSet<>();

    public ShopImpl() {
        loadDb();
    }

    private void loadDb(){
        // грузить из файла/бд
        db = (new ShopDbGenerator()).generate();
    }

    public User registerUser() throws Exception {
        try {
            String login = getAnswer("Введите логин:", true);
            if (db.getUsers().stream().anyMatch(user -> user.getLogin().equals(login)))
                System.out.println("Такой логин уже зарегистрирован в системе. Введите другой");
            String password = getAnswer("Введите пароль:", true);
            String name = getAnswer("Введите ваше имя:", true);
            User user = new User(login, password, name);
            db.addUser(user);
            return user;
        } catch (Exception e) {
            throw new Exception("Ошибка регистрации клиента");
        }
    }

    public User getUser(String login, String password){
        boolean res = db.getUsers().stream().noneMatch(x -> x.getLogin().equals(login));
        if (res){
            System.out.println("Такого пользователя не существует");
            return null;
        }
        User user = db.getUsers().stream()
                .filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password))
                .findAny().get();
        if (user == null){
            System.out.println("Пароль неверный");
        }
        return user;
    }

    public Set<Productik> getProducts(){
        return db.getProducts();
    }

    public Set<Order> getOrders(){
        return db.getOrders();
    }

    public Set<Order> getOrders(User user){
        return db.getOrders().stream().filter(x -> x.getUser() == user).collect(Collectors.toSet());
    }

    public Order getOrder(int orderId){
        return db.getOrders().stream().filter(x -> x.getId() == orderId).findFirst().get();
    }

    public Map<UserProduct, Double> getRatings(){ return db.getRatings();}

    public void addRating(User user, Productik product, Double rating){
        db.addRating(user, product, rating);
    }

    public void removeOrder(int orderId){
        Set<Order> orders = db.getOrders();
        if (orders.stream().anyMatch(x -> x.getId() == orderId))
            orders.remove(orders.stream().filter(x -> x.getId() == orderId).findAny());
    }

    public Set<User> getUsers(){
        return db.getUsers();
    }
}
