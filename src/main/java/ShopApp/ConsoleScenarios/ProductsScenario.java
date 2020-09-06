package ShopApp.ConsoleScenarios;

import ShopApp.ProductTools.Productik;
import ShopApp.ProductTools.ProductsFilter;
import ShopApp.ShopImpl;
import ShopApp.User;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProductsScenario extends BaseConsoleScenario {
    private User user;
    private ShopImpl shop;
    private ProductsFilter filter;

    public ProductsScenario(User user, ShopImpl shop) {
        this.user = user;
        this.shop = shop;
    }

    public void showProductsScenario() {
        filter = new ProductsFilter(() -> shop.getProducts());
        printProducts(filter.filter());
        pressEnter();

        while (true) {
            int answer = getAnswer("== Выберите дальнейшее действие\n"
                    + "1. добавить продукт в заказ\n"
                    + "2. упорядочить по популярности\n"
                    + "3. упорядочить по рейтингу\n"
                    + "4. указать минимальную цену\n"
                    + "5. указать максимальную цену\n"
                    + "6. добавить ключевое слово в фильтр\n"
                    + "7. отключить фильтры\n"
                    + "8. показать рекомендуемые товары\n"
                    + "9. показать ключевые слова\n"
                    + "10. оформить заказ\n"
                    + "11. поставить товару оценку\n"
                    + "0. вернуться назад\n", 0, 11);
            switch (answer){
                case 1:
//                    addProduct();
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

                case 9:
                    printKeywords();
                    pressEnter();
                    break;
                case 10:
                case 11:
                case 0:
                    return;

            }
        }
    }

    private void printKeywords() {
        Set<String> keywords = shop.getProducts().stream().flatMap(x -> x.getKeywords().stream()).collect(Collectors.toSet());
        System.out.println("== Ключевые слова:");
        for (String keyword : keywords){
            System.out.println(keyword);
        }
    }


}
