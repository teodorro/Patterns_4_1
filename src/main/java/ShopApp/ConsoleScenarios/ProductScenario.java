package ShopApp.ConsoleScenarios;

import ShopApp.Model.IShop;
import ShopApp.Model.ProductTools.*;
import ShopApp.Model.User;

public class ProductScenario extends BaseConsoleScenario {
    private final String NAME = "Name";
    private final String PRICE = "Price";
    private final String PRODUCER = "Producer";
    private final String RATING = "Rating";
    private final String KEYWORDS = "Keywords";
    private IShop shop;
    private User user;
    private Productik product;

    public ProductScenario(User user, IShop shop, Productik product) {
        this.user = user;
        this.shop = shop;
        this.product = product;
    }

    public void showScenario(){
        showProductInfo(user);
        while (true) {
            int answer = getAnswer("Выберите желаемое действие:\n"
                    + "1. Добавить продукт в заказ\n"
                    + "2. Поставить товару оценку\n"
                    + "3. Отменить оценку товара\n"
                    + "0. Вернуться назад", 0, 3);
            switch (answer) {
                case 1:
                    Double quantity = getAnswer("Укажите количество продукта:", 0d, Double.MAX_VALUE);
                    user.getCurrentOrder().setProduct(product, quantity);
                    System.out.println("Товар добавлен в заказ");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showProductInfo(User user)
    {
        String keywords = product.getKeywords() != null ? product.getKeywords().stream().reduce((x, y) -> x + ", " + y).get() : "";
        int maxNameLength = Math.max(NAME.length(), Math.max(PRICE.length(), Math.max(RATING.length(), Math.max(PRODUCER.length(), KEYWORDS.length()))));
        int maxValueLength = Math.max(product.getName().length(), Math.max(product.getPrice().toString().length(), Math.max(product.getProducer().length(), keywords.length())));
        System.out.println(cellVal(maxNameLength, NAME) + cellVal(maxValueLength, product.getName())
            + "\n" + cellVal(maxNameLength, PRICE) + cellVal(maxValueLength, product.getPrice().toString())
            + "\n" + cellVal(maxNameLength, PRODUCER) + cellVal(maxValueLength, product.getProducer())
            + "\n" + cellVal(maxNameLength, RATING) + cellVal(maxValueLength, product.getRating(user).toString())
            + "\n" + cellVal(maxNameLength, KEYWORDS) + cellVal(maxValueLength, keywords) + "\n");
    }
}
