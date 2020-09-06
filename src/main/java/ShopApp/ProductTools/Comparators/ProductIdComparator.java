package ShopApp.ProductTools.Comparators;

import ShopApp.ProductTools.Productik;

import java.util.Comparator;

public class ProductIdComparator implements Comparator<Productik> {
    @Override
    public int compare(Productik product1, Productik product2) {
        return product1.getId() - product2.getId();
    }
}
