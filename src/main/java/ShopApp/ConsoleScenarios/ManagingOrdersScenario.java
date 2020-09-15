package ShopApp.ConsoleScenarios;

import ShopApp.Model.*;
import ShopApp.Model.ProductTools.Comparators.ProductIdComparator;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ManagingOrdersScenario extends BaseConsoleScenario{
    private User user;
    private ShopUser shop;

    public ManagingOrdersScenario(User user, ShopUser shop) {
        this.user = user;
        this.shop = shop;
    }

    public void showOrdersScenario() {
        while (true) {
            int answer = getAnswer("== Select action:\n"
                    + "1. See previous orders\n"
                    + "2. See order content\n"
                    + "3. Cancel order\n"
                    + "4. Return order\n"
                    + "5. Repeat order\n"
                    + "0. Back", 0, 5);
            switch (answer) {
                case 1:
                    showOrders();
                    pressEnter();
                    break;
                case 2:
                    int orderId = getAnswer("Write order id:", 0, null);
                    TreeSet<Productik> products = new TreeSet<>(new ProductIdComparator());
                    products.addAll(shop.getOrder(orderId).getProducts());
                    printProducts(products, user);
                    break;
                case 3:
                    removeOrder(getAnswer("Write order id:", 0, null));
                    showOrders();
                    pressEnter();
                    break;
                case 4:
                    returnOrder(getAnswer("Write order id:", 0, null));
                    showOrders();
                    pressEnter();
                    break;
                case 5:
                    repeatOrder(getAnswer("Write order id:", 0, null));
                    showOrders();
                    pressEnter();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void returnOrder(int idOrder) {
        Order order = shop.getOrder(idOrder);
        if (order == null){
            System.out.println("No order with this id");
            return;
        }
        if (order.getState() != OrderState.DELIVERED){
            System.out.println("This order cannot be returned");
            return;
        }
        order.setState(OrderState.RETURNED);
    }

    private void repeatOrder(int idOrder) {
        Order order = shop.getOrder(idOrder);
        if (order == null){
            System.out.println("No order with this id");
            return;
        }
        shop.copy(idOrder);
    }

    private void removeOrder(int id) {
        shop.removeOrder(id);
    }

    private void showOrders() {
        printOrders(shop.getOrders(user));
    }

    private void printOrders(List<Order> orders) {
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
