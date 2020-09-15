package ShopApp.ConsoleScenarios;

import ShopApp.Model.*;
import ShopApp.Model.ProductTools.Comparators.ProductIdComparator;

import java.text.DecimalFormat;
import java.util.*;

public class ManagingOrdersScenario extends BaseConsoleScenario{
    private final String AMOUNT = "Amount";

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
//                    TreeSet<Productik> products = new TreeSet<>(new ProductIdComparator());
//                    products.addAll(shop.getOrder(orderId).getProducts());
//                    printProducts(products, user);
                    printOrder(shop.getOrder(orderId));
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

    private void printOrder(Order order) {
        Map<Productik, Double> productAmounts = order.getProductAmounts();
        Set<Productik> products = order.getProducts();
        int maxLengthName = Math.max(NAME.length(), products.stream().map(x -> x.getName()).map(y -> y.length()).max(Comparator.naturalOrder()).get());
        int maxLengthProducer = Math.max(PRODUCER.length(), products.stream().map(x -> x.getProducer()).map(y -> y != null ? y.length() : 0).max(Comparator.naturalOrder()).get());
        int maxPrice = Math.max(PRICE.length(), products.stream().map(x -> x.getPrice()).max(Comparator.naturalOrder()).get().toString().length());

        System.out.println(cellVal(maxLengthName, NAME)
                + cellVal(maxLengthProducer, PRODUCER)
                + cellVal(AMOUNT.length(), AMOUNT)
                + cellVal(maxPrice, PRICE)
        );
        System.out.println("-".repeat(maxLengthName + maxPrice + maxLengthProducer + AMOUNT.length() + COLUMN_GAP * 3));
        for (Productik product : products) {
            Double amount = productAmounts.get(product);
            System.out.println(cellVal(maxLengthName, product.getName())
                    + cellVal(maxLengthProducer, product.getProducer() == null ? "" : product.getProducer() )
                    + cellVal(AMOUNT.length(), new DecimalFormat("0.00").format(amount))
                    + cellVal(maxPrice, String.valueOf(product.getPrice() * amount))
            );
        }
        System.out.println("");
    }

    private void returnOrder(int id) {
        Order order = shop.getOrder(id);
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

    private void repeatOrder(int id) {
        Order order = shop.getOrder(id);
        if (order == null){
            System.out.println("No order with this id");
            return;
        }
        Order order2 = shop.copy(id).setState(OrderState.PREPARING);
        shop.getOrders().add(order2);
    }

    private void removeOrder(int id) {
        Order order = shop.getOrder(id);
        if (order == null){
            System.out.println("No order with this id");
            return;
        }
        if (order.getState() != OrderState.PREPARING && order.getState() != OrderState.CONSTRUCTING){
            System.out.println("This order cannot be removed");
            return;
        }
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
        System.out.println("");
    }
}
