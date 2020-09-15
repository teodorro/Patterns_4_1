package ShopApp.ConsoleScenarios;

import ShopApp.Model.Productik;
import ShopApp.Model.ShopUser;
import ShopApp.Model.User;
import ShopApp.Model.UserProduct;

public class ProductScenario extends BaseConsoleScenario {
    private final String NAME = "Name";
    private final String PRICE = "Price";
    private final String PRODUCER = "Producer";
    private final String RATING = "Rating";
    private final String KEYWORDS = "Keywords";
    private ShopUser shop;
    private User user;
    private Productik product;

    public ProductScenario(User user, ShopUser shop, Productik product) {
        this.user = user;
        this.shop = shop;
        this.product = product;
    }

    public void showScenario(){
        showProductInfo(user);
        while (true) {
            int answer = getAnswer("== Select action:\n"
                    + "1. Add product to order\n"
                    + "2. Set rating\n"
                    + "3. Cancel rating\n"
                    + "0. Back", 0, 3);
            switch (answer) {
                case 1:
                    addProductToOrder();
                    break;
                case 2:
                    setRating();
                    break;
                case 3:
                    cancelRating();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void cancelRating() {
        shop.setRating(new UserProduct(user, product), null);
    }

    private void setRating() {
        Double rating = getAnswer("Write rating value (1-5):", 1d, 5d);
        shop.setRating(new UserProduct(user, product), rating);
    }

    private void addProductToOrder() {
        Double quantity = getAnswer("Write amount of product:", 0d, Double.MAX_VALUE);
        user.getCurrentOrder().setProduct(product, quantity);
        System.out.println("Product added");
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
