package ShopApp.Model.ProductTools.Comparators;

import ShopApp.Model.ProductTools.*;

import java.util.Comparator;

public class ProductPopularityComparator implements Comparator<Productik> {
    @Override
    public int compare(Productik product1, Productik product2) {
        return Comparator.comparing(Productik::getNumberSells)
                .thenComparing(Productik::getName)
                .compare(product2, product1);
    }

}
