package ShopApp.ConsoleScenarios;

import ShopApp.Db.DtoConverter;
import ShopApp.Db.ShopDbTest;
import ShopApp.Model.IShop;
import ShopApp.Model.IShopSetData;
import ShopApp.Model.ProductTools.ProductBuilder;
import ShopApp.Model.ShopImpl2;
import ShopApp.Model.User;

public class ConsoleScenario extends BaseConsoleScenario {

    private IShop shop;
    private User user;

    public ConsoleScenario() {
        ShopDbTest db = new ShopDbTest().Initialize();
        shop = new ShopImpl2();
        ProductBuilder.setShop(shop);
        new DtoConverter().convert(db, (IShopSetData)shop);
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
