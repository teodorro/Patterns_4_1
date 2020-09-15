package ShopApp.Model.ProductTools.Comparators;

import ShopApp.Model.Productik;
import ShopApp.Model.User;

import java.util.Comparator;

public class ProductRatingComparator implements Comparator<Productik> {
    private User user;

    public ProductRatingComparator(User user) {
        this.user = user;
    }

    @Override
    public int compare(Productik product1, Productik product2) {
//        return Comparator.comparing(Productik::getRatingZeroFromNull)
        return Comparator.comparing((Productik product) -> product.getRatingZeroFromNull2(user))
                .thenComparing(Productik::getName)
                .compare(product2, product1);
    }
}
