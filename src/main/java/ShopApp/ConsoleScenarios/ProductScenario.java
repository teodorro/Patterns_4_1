package ShopApp.ConsoleScenarios;

import ShopApp.Model.*;

public class ProductScenario extends BaseConsoleScenario {
    private final String NAME = "Name";
    private final String PRICE = "Price";
    private final String PRODUCER = "Producer";
    private final String AVG_RATING = "Avg rating";
    private final String USER_RATING = "User rating";
    private final String KEYWORDS = "Keywords";
    private ShopUser shop;
    private User user;
    private Productik product;

    public ProductScenario(User user, ShopUser shop, Productik product) {
        this.user = user;
        this.shop = shop;
        this.product = product;
    }

    public void showScenario() {
        showProductInfo(user);
        boolean wasOrderedLater = user.getOrders().stream().anyMatch(order -> order.getState() == OrderState.DELIVERED)
                ? user.getOrders().stream().filter(o -> o.getState() == OrderState.DELIVERED).flatMap(or -> or.getProducts().stream()).anyMatch(p -> p == product)
                : false;
        boolean ratingWasSet = user.getRatings().containsKey(product);
        while (true) {
            int answer = getAnswer("== Select action:\n"
                    + "1. Add product to order\n"
                    + (wasOrderedLater ? "2. Set rating\n" : "")
                    + (ratingWasSet ? "3. Cancel rating\n" : "")
                    + "0. Back", 0, 3);
            switch (answer) {
                case 1:
                    addProductToOrder();
                    return;
                case 2:
                    if (wasOrderedLater)
                        setRating();
                    return;
                case 3:
                    if (ratingWasSet)
                        cancelRating();
                    return;
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

    private void showProductInfo(User user) {
        System.out.println("== Product info:");
        String keywords = product.getKeywords() != null && product.getKeywords().size() > 0 ? product.getKeywords().stream().reduce((x, y) -> x + ", " + y).get() : "";
        int maxNameLength = Math.max(NAME.length(), Math.max(PRICE.length(), Math.max(AVG_RATING.length(), Math.max(USER_RATING.length(), Math.max(PRODUCER.length(), KEYWORDS.length())))));
        int maxValueLength = Math.max(product.getName().length(), Math.max(product.getPrice().toString().length(), Math.max(product.getProducer().length(), keywords.length())));
        System.out.println(cellVal(maxNameLength, NAME) + cellVal(maxValueLength, product.getName())
                + "\n" + cellVal(maxNameLength, PRICE) + cellVal(maxValueLength, product.getPrice().toString())
                + "\n" + cellVal(maxNameLength, PRODUCER) + cellVal(maxValueLength, product.getProducer())
                + "\n" + cellVal(maxNameLength, AVG_RATING) + cellVal(maxValueLength, product.getAvgRating().toString())
                + "\n" + cellVal(maxNameLength, USER_RATING) + cellVal(maxValueLength, product.getRatingSetByUser(user) != null ? product.getRatingSetByUser(user).toString() : "")
                + "\n" + cellVal(maxNameLength, KEYWORDS) + cellVal(maxValueLength, keywords) + "\n");
    }
}
