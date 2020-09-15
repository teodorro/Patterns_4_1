package ShopApp.ConsoleScenarios;

import ShopApp.Model.*;
import ShopApp.Model.ProductTools.ProductsFilter;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChooseProductsScenario extends BaseConsoleScenario {
    private User user;
    private ShopUser shop;
    private ProductsFilter filter;

    public ChooseProductsScenario(User user, ShopUser shop) {
        this.user = user;
        this.shop = shop;
    }

    public void showProductsScenario() {
        filter = new ProductsFilter(() -> shop.getProducts(), user);
        printProducts(filter.filter(), user);
        pressEnter();

        while (true) {
            int answer = getAnswer("== Select action:\n"
                    + "1. Choose a product\n"
                    + "2. Order by popularity\n"
                    + "3. Order by rating\n"
                    + "4. Set minimum price\n"
                    + "5. Set maximum price\n"
                    + "6. Add keyword to the filter\n"
                    + "7. Clear filter\n"
                    + "8. Show recommended products\n"
                    + "9. Show all keywords\n"
                    + (user.getCurrentOrder().getProducts().size() > 0
                    ? "10. Show current order content\n"
                    + "11. Next to order finalization\n"
                    : "")
                    + "0. Back\n", 0, 11);
            switch (answer) {
                case 1:
                    String productName = getAnswer("Write a product name:", true);
                    Productik product = shop.getProduct(productName);
                    if (product != null)
                        (new ProductScenario(user, shop, product))
                                .showScenario();
                    else
                        System.out.println("Product not found");
                    break;
                case 2:
                    filter.setMostPopular(true);
                    printProducts(filter.filter(), user);
                    pressEnter();
                    break;
                case 3:
                    filter.setHighestRating(true);
                    printProducts(filter.filter(), user);
                    pressEnter();
                    break;
                case 4:
                    filter.setMinPrice(getAnswer("Write a minimum price:", 0d, filter.getMaxPrice()));
                    printProducts(filter.filter(), user);
                    pressEnter();
                    break;
                case 5:
                    filter.setMaxPrice(getAnswer("Write a maximum price:", filter.getMinPrice() != null ? filter.getMinPrice() : 0, null));
                    printProducts(filter.filter(), user);
                    pressEnter();
                    break;
                case 6:
                    filter.getKeywords().add(getAnswer("Write a keyword:", true));
                    printProducts(filter.filter(), user);
                    pressEnter();
                    break;
                case 7:
                    filter.clear();
                    printProducts(filter.filter(), user);
                    pressEnter();
                    break;
                case 8:
                    printProducts(filter.getRecommended(user, shop.getUsers()), user);
                    pressEnter();
                    break;
                case 9:
                    printAllKeywords();
                    pressEnter();
                    break;
                case 10:
                    if (user.getCurrentOrder().getProducts().size() > 0)
                    {
                        printCurrentOrder();
                        pressEnter();
                    }
                    break;
                case 11:
                    if (user.getCurrentOrder().getProducts().size() > 0) {
                        printCurrentOrder();
                        if (user.getCurrentOrder() != null) {
                            if (getAnswerYesNo("Confirm ordering (yes/no)?")) {
                                user.getCurrentOrder().setState(OrderState.PREPARING);
                                shop.addOrder(user.getCurrentOrder());
                                user.setCurrentOrder(null);
                                return;
                            }
                        }
                    }
                    break;
                case 0:
                    return;
            }
        }
    }

    private void printCurrentOrder() {
        Order order = user.getCurrentOrder();
        if (order == null) {
            System.out.println("Current order is empty");
            return;
        }
        TreeSet<Productik> orderProducts = new TreeSet<>();
        orderProducts.addAll(order.getProducts());
        System.out.println("== Current order content");
        this.printProducts(orderProducts, user);
    }

    private void printAllKeywords() {
        Set<String> keywords = shop.getKeywords();
        System.out.println("== Keywords:");
        for (String keyword : keywords) {
            System.out.println(keyword);
        }
    }

    protected void printProducts(TreeSet<Productik> products, User user) {
        int maxLengthName = Math.max(NAME.length(), products.stream().map(x -> x.getName()).map(y -> y.length()).max(Comparator.naturalOrder()).get());
        int maxLengthProducer = Math.max(PRODUCER.length(), products.stream().map(x -> x.getProducer()).map(y -> y != null ? y.length() : 0).max(Comparator.naturalOrder()).get());
        int maxPrice = Math.max(PRICE.length(), products.stream().map(x -> x.getPrice()).max(Comparator.naturalOrder()).get().toString().length());

        System.out.println(cellVal(maxLengthName, NAME)
                + cellVal(maxPrice, PRICE)
                + cellVal(maxLengthProducer, PRODUCER)
                + cellVal(RATING.length(), RATING)
        );
        System.out.println("-".repeat(maxLengthName + maxPrice + maxLengthProducer + + RATING.length() + COLUMN_GAP * 3));
        for (Productik product : products) {
            Double rating = product.getRatingSetByUser(user);
            if (rating == null)
                rating = product.getAvgRating();
            System.out.println(cellVal(maxLengthName, product.getName())
                    + cellVal(maxPrice, product.getPrice().toString())
                    + cellVal(maxLengthProducer, product.getProducer() == null ? "" : product.getProducer() )
                    + cellVal(RATING.length(), rating == null ? "" : new DecimalFormat("0.00").format(rating))
            );
        }
    }


}
