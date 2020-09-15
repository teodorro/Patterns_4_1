package ShopApp.ConsoleScenarios;

import ShopApp.Db.DtoConverter;
import ShopApp.Db.ShopDbTest;
import ShopApp.Model.*;
import ShopApp.Model.ProductTools.ProductBuilder;

public class ConsoleScenario extends BaseConsoleScenario {

    private ShopUser shop;
    private User user;

    public ConsoleScenario() {
        ShopDbTest db = new ShopDbTest().Initialize();
        shop = new Shop();
        ProductBuilder.setShop(shop);
        UserBuilder.setShop(shop);
        new DtoConverter().convert(db, (ShopSetData)shop);
    }

    public void start() {
//        if (!(new LoginScenario(user, shop).loginOrRegister()))
//            return;
        user = shop.getUser("user1", "123");
        showProductsOrders();
    }

    public void showProductsOrders() {
        while (true) {
            int answer = getAnswer("== Select action:\n"
                    + "1. Choose products\n"
                    + "2. See orders\n"
                    + "0. Exit", 0, 2);
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
