package ShopApp.ConsoleScenarios;


import ShopApp.Model.ProductTools.*;
import ShopApp.Model.User;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.TreeSet;

public class BaseConsoleScenario extends InputProcessor {
    protected final String NAME = "Name";
    protected final String PRODUCER = "Producer";
    protected final String PRICE = "Price";
    protected final String RATING = "Rating";
    protected final int COLUMN_GAP = 3;
    protected final String ID = "Id";
    protected final String LAST_TIME_MODIFIED = "Last time modified";
    protected final String STATE = "State";

    protected String cellVal(int length, String content){
        return String.format("%-" + (length + COLUMN_GAP) + "s", content);
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
//            Object a = product.getRating(user);
            System.out.println(cellVal(maxLengthName, product.getName())
                    + cellVal(maxPrice, product.getPrice().toString())
                    + cellVal(maxLengthProducer, product.getProducer() == null ? "" : product.getProducer() )
                    + cellVal(RATING.length(), product.getRating(user) == null ? "" : new DecimalFormat("0.00").format(product.getRating(user)))
            );
        }
    }

}
