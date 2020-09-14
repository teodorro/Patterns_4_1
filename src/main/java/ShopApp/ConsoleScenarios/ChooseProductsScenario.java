package ShopApp.ConsoleScenarios;

import ShopApp.Model.IShop;
import ShopApp.Model.Order;
import ShopApp.Model.ProductTools.*;
import ShopApp.Model.ProductTools.ProductsFilter;
import ShopApp.Model.User;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChooseProductsScenario extends BaseConsoleScenario {
    private User user;
    private IShop shop;
    private ProductsFilter filter;

    public ChooseProductsScenario(User user, IShop shop) {
        this.user = user;
        this.shop = shop;
    }

    public void showProductsScenario() {
        filter = new ProductsFilter(() -> shop.getProducts(), user);
        printProducts(filter.filter(), user);
        pressEnter();

        while (true) {
            int answer = getAnswer("== Choose what to do:\n"
                    + "1. Choose a product\n"
                    + "2. Order by popularity\n"
                    + "3. Order by rating\n"
                    + "4. Set minimum price\n"
                    + "5. Set maximum price\n"
                    + "6. Add keyword to the filter\n"
                    + "7. Clear filter\n"
                    + "8. Show recommended products\n"
                    + "9. Show all keywords\n"
                    + "10. Show current order content\n"
                    + "11. Next to order finalization\n"
                    + "0. Back\n", 0, 11);
            switch (answer){
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
                    printKeywords();
                    pressEnter();
                    break;
                case 10:
                    printCurrentOrder();
                    pressEnter();
                    break;
                case 11:
                    printCurrentOrder();
                    if (getAnswerYesNo("Confirm ordering (yes/no)?")){

                    }

                case 0:
                    return;

            }
        }
    }

    private void printCurrentOrder() {
        Order order = user.getCurrentOrder();
        if (order == null){
            System.out.println("Текущий заказ пуст");
            return;
        }
        TreeSet<Productik> orderProducts = new TreeSet<>();
        orderProducts.addAll(order.getProducts());
        this.printProducts(orderProducts, user);
    }

    private void printKeywords() {
        Set<String> keywords = shop.getProducts().stream().flatMap(x -> x.getKeywords().stream()).collect(Collectors.toSet());
        System.out.println("== Ключевые слова:");
        for (String keyword : keywords){
            System.out.println(keyword);
        }
    }


}
