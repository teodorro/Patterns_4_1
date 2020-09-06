package ShopApp.ProductTools.Comparators;

import ShopApp.ProductTools.Productik;

import java.util.Comparator;

public class ProductRatingComparator implements Comparator<Productik> {
    @Override
    public int compare(Productik product1, Productik product2) {
        return Comparator.comparing(Productik::getRatingZero)
                .thenComparing(Productik::getName)
                .compare(product2, product1);
    }
}
