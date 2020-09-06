package ShopApp.ConsoleScenarios;

import ShopApp.InputProcessor;
import ShopApp.Order;
import ShopApp.ProductTools.Comparators.ProductIdComparator;
import ShopApp.ProductTools.Productik;
import ShopApp.ProductTools.ProductsFilter;
import ShopApp.ShopImpl;
import ShopApp.User;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ConsoleScenario extends BaseConsoleScenario {

    private ShopImpl shop;
    private User user;

    public ConsoleScenario(ShopImpl shop) {
        this.shop = shop;
    }

    public void start() {
        if (!(new LoginScenario(user, shop).loginOrRegister()))
            return;
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
                    (new ProductsScenario(user, shop)).showProductsScenario();
                    break;
                case 2:
                    (new OrdersScenario(user, shop)).showOrdersScenario();
                    break;
                case 0:
                    return;
            }
        }
    }


}
