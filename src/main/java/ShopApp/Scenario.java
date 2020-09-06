package ShopApp;

import ShopApp.ProductTools.Productik;
import ShopApp.ProductTools.ProductsFilter;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Scenario extends InputProcessor {
    private final String NAME = "Name";
    private final String PRODUCER = "Producer";
    private final String PRICE = "Price";
    private final String RATING = "Rating";
    private final int COLUMN_GAP = 3;

    private ShopImpl shop;
    private User user;
    private ProductsFilter filter;

    public Scenario(ShopImpl shop) {
        this.shop = shop;
    }

    public void start() {
//        if (!loginOrRegister())
//            return;
        user = shop.getUser("user1", "123");
        showProductsOrders();
    }

    public void showProductsOrders() {
        while (true) {
            int answer = getAnswer("Выберите желаемое действие:\n"
                    + "1. Выбрать товары\n"
                    + "2. Посмотреть заказы\n"
                    + "0. Выход", 0, 2);
            switch (answer) {
                case 1:
                    showProductsScenario();
                    break;
                case 2:
                    showOrders();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showOrders() {
    }

    private void showProductsScenario() {
        filter = new ProductsFilter(() -> shop.getProducts());
        printProducts(filter.filter());
        pressEnter();

        while (true) {
            int answer = getAnswer("== Выберите дальнейшее действие\n"
                    + "1. добавить продукт в заказ\n"
                    + "2. упорядочить по популярности\n"
                    + "3. упорядочить по рейтингу\n"
                    + "4. указать минимальную цену\n"
                    + "5. указать максимальную цену\n"
                    + "6. добавить ключевое слово в фильтр\n"
                    + "7. отключить фильтры\n"
                    + "8. показать рекомендуемые товары\n"
                    + "9. показать ключевые слова\n"
                    + "0. вернуться назад\n", 0, 8);
            switch (answer){
                case 1:
//                    addProduct();
                    break;
                case 2:
                    filter.setMostPopular(true);
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 3:
                    filter.setHighestRating(true);
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 4:
                    filter.setMinPrice(getAnswer("Введите минимальную цену", 0d, filter.getMaxPrice()));
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 5:
                    filter.setMaxPrice(getAnswer("Введите максимальную цену", filter.getMinPrice() != null ? filter.getMinPrice() : 0, null));
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 6:
                    filter.getKeywords().add(getAnswer("Введите ключевое слово", true));
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 7:
                    filter.clear();
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 8:

                case 9:
                    printKeywords();
                    break;
                case 0:
                    return;

            }
        }
    }

    private void printKeywords() {
        Set<String> keywords = shop.getProducts().stream().flatMap(x -> x.getKeywords().stream()).collect(Collectors.toSet());
        System.out.println("== Ключевые слова:");
        for (String keyword : keywords){
            System.out.println(keyword);
        }
    }

    private void printProducts(TreeSet<Productik> products) {
        int maxLengthName = Math.max(NAME.length(), products.stream().map(x -> x.getName()).map(y -> y.length()).max(Comparator.naturalOrder()).get());
        int maxLengthProducer = Math.max(PRODUCER.length(), products.stream().map(x -> x.getProducer()).map(y -> y != null ? y.length() : 0).max(Comparator.naturalOrder()).get());
        int maxPrice = Math.max(PRICE.length(), products.stream().map(x -> x.getPrice()).max(Comparator.naturalOrder()).get().toString().length());

        System.out.println(cellVal(maxLengthName, NAME)
                + cellVal(maxPrice, PRICE)
                + cellVal(maxLengthProducer, PRODUCER)
                + cellVal(RATING.length(), RATING)
        );
        for (Productik product : products) {
            Object a = product.getRating();
            System.out.println(cellVal(maxLengthName, product.getName())
                    + cellVal(maxPrice, product.getPrice().toString())
                    + cellVal(maxLengthProducer, product.getProducer() == null ? "" : product.getProducer() )
                    + cellVal(RATING.length(), product.getRating() == null ? "" : new DecimalFormat("0.00").format(product.getRating()))
            );
        }
    }

    private String cellVal(int length, String content){
        return String.format("%-" + (length + COLUMN_GAP) + "s", content);
    }

    public boolean loginOrRegister() {
        while (true) {
            try {
                int answer = getAnswer("Выберите желаемое действие:\n"
                                + "1. Войти в свой кабинет\n"
                                + "2. Зарегистрироваться\n"
                                + "0. Выход",
                        0, 2);
                switch (answer) {
                    case 1:
                        user = login();
                        break;
                    case 2:
                        user = shop.registerUser();
                        break;
                    default:
                        return false;
                }
                if (user != null)
                    return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private User login() {
        while (true) {
            String login = getAnswer("Введите логин или нажмите enter, чтобы вернуться назад:", true);
            if (login.isEmpty())
                return null;
            String password = getAnswer("Введите пароль:", true);
            User user = shop.getUser(login, password);
            if (user == null)
                continue;
            return user;
        }
    }

}
