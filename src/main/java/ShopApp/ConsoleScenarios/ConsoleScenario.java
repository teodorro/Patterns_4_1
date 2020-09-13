package ShopApp.ConsoleScenarios;

import ShopApp.ShopImpl;
import ShopApp.User;

public class ConsoleScenario extends BaseConsoleScenario {

    private ShopImpl shop;
    private User user;

    public ConsoleScenario(ShopImpl shop) {
        this.shop = shop;
    }

    public void start() {
//        if (!(new LoginScenario(user, shop).loginOrRegister()))
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
                    (new ChooseProductsScenario(user, shop)).showProductsScenario();
                    break;
                case 2:
                    (new ManagingOrdersScenario(user, shop)).showOrdersScenario();
                    break;
                case 0:
                    return;
            }
        }
    }


}
