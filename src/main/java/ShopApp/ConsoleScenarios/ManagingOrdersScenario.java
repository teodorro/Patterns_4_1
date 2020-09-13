package ShopApp.ConsoleScenarios;

import ShopApp.Order;
import ShopApp.ProductTools.Comparators.ProductIdComparator;
import ShopApp.ProductTools.Productik;
import ShopApp.ShopImpl;
import ShopApp.User;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ManagingOrdersScenario extends BaseConsoleScenario{
    private User user;
    private ShopImpl shop;

    public ManagingOrdersScenario(User user, ShopImpl shop) {
        this.user = user;
        this.shop = shop;
    }

    public void showOrdersScenario() {
        while (true) {
            int answer = getAnswer("Выберите желаемое действие:\n"
                    + "1. Посмотреть прошлые заказы\n"
                    + "2. Посмотреть содержимое заказа\n"
                    + "3. Отмена заказа\n"
                    + "4. Возврат заказа\n"
                    + "5. Повторение заказа\n"
                    + "0. Выход", 0, 5);
            switch (answer) {
                case 1:
                    showOrders();
                    pressEnter();
                    break;
                case 2:
                    int orderId = getAnswer("Введите id заказа:", 0, null);
                    TreeSet<Productik> products = new TreeSet<>(new ProductIdComparator());
                    products.addAll(shop.getOrder(orderId).getProducts());
                    printProducts(products);
                    break;
                case 3:
                    removeOrder(getAnswer("Введите id заказа:", 0, null));
                    showOrders();
                    pressEnter();
                    break;
                case 4:
                    returnOrder(getAnswer("Введите id заказа:", 0, null));
                    showOrders();
                    pressEnter();
                    break;
                case 5:
                    repeatOrder(getAnswer("Введите id заказа:", 0, null));
                    showOrders();
                    pressEnter();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void returnOrder(int idOrder) {

    }

    private void repeatOrder(int idOrder) {

    }

    private void removeOrder(int id) {
        shop.removeOrder(id);
    }

    private void showOrders() {
        printOrders(shop.getOrders(user));
    }

    private void printOrders(Set<Order> orders) {
        int maxLengthId = Math.max(ID.length(), orders.stream().map(x -> x.getId()).max(Comparator.naturalOrder()).get().toString().length());
        int maxLengthTime = Math.max(LAST_TIME_MODIFIED.length(), orders.stream().map(x -> x.getLastTimeModified()).max(Comparator.naturalOrder()).get().toString().length());
        int maxLengthState = Math.max(STATE.length(), orders.stream().map(x -> x.getState().toString()).map(y -> y.length()).max(Comparator.naturalOrder()).get());

        System.out.println(cellVal(maxLengthId, ID)
                + cellVal(maxLengthTime, LAST_TIME_MODIFIED)
                + cellVal(maxLengthState, STATE)
        );
        System.out.println("-".repeat(maxLengthId + maxLengthTime + maxLengthState + COLUMN_GAP * 2));
        for (Order order : orders){
            System.out.println(cellVal(maxLengthId, String.valueOf(order.getId()))
                    + cellVal(maxLengthTime, order.getLastTimeModified().toString())
                    + cellVal(maxLengthState, order.getState().toString())
            );
        }
    }
}
