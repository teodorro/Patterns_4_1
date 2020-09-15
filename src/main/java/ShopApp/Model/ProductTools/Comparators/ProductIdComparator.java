package ShopApp.Model.ProductTools.Comparators;

import ShopApp.Model.Productik;

import java.util.Comparator;

public class ProductIdComparator implements Comparator<Productik> {
    @Override
    public int compare(Productik product1, Productik product2) {
        return product1.getName().compareTo(product2.getName());
    }
}
