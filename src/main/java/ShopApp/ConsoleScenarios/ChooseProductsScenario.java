package ShopApp.ConsoleScenarios;

import ShopApp.Order;
import ShopApp.ProductTools.Productik;
import ShopApp.ProductTools.ProductsFilter;
import ShopApp.ShopImpl;
import ShopApp.User;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChooseProductsScenario extends BaseConsoleScenario {
    private User user;
    private ShopImpl shop;
    private ProductsFilter filter;

    public ChooseProductsScenario(User user, ShopImpl shop) {
        this.user = user;
        this.shop = shop;
    }

    public void showProductsScenario() {
        filter = new ProductsFilter(() -> shop.getProducts());
        printProducts(filter.filter());
        pressEnter();

        while (true) {
            int answer = getAnswer("== Выберите дальнейшее действие\n"
                    + "1. выбрать продукт\n"
                    + "2. упорядочить по популярности\n"
                    + "3. упорядочить по рейтингу\n"
                    + "4. указать минимальную цену\n"
                    + "5. указать максимальную цену\n"
                    + "6. добавить ключевое слово в фильтр\n"
                    + "7. отключить фильтры\n"
                    + "8. показать рекомендуемые товары\n"
                    + "9. показать ключевые слова\n"
                    + "10. посмотреть текущий заказ\n"
                    + "11. перейти к оформлению заказа\n"
                    + "0. вернуться назад\n", 0, 11);
            switch (answer){
                case 1:
                    String productName = getAnswer("Напишите название продукта:", true);
                    if (shop.getProducts().stream().anyMatch(x -> x.getName().equals(productName)))
                        (new ProductScenario(user, shop, shop.getProducts().stream().filter(x -> x.getName().equals(productName)).findFirst().get()))
                                .showScenario();
                    else
                        System.out.println("Продукт не найден");
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
                    filter.setMinPrice(getAnswer("Введите минимальную цену:", 0d, filter.getMaxPrice()));
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 5:
                    filter.setMaxPrice(getAnswer("Введите максимальную цену:", filter.getMinPrice() != null ? filter.getMinPrice() : 0, null));
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 6:
                    filter.getKeywords().add(getAnswer("Введите ключевое слово:", true));
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 7:
                    filter.clear();
                    printProducts(filter.filter());
                    pressEnter();
                    break;
                case 8:
                    printProducts(filter.getRecommended(user, shop.getUsers()));
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
        this.printProducts(orderProducts);
    }

    private void printKeywords() {
        Set<String> keywords = shop.getProducts().stream().flatMap(x -> x.getKeywords().stream()).collect(Collectors.toSet());
        System.out.println("== Ключевые слова:");
        for (String keyword : keywords){
            System.out.println(keyword);
        }
    }


}
