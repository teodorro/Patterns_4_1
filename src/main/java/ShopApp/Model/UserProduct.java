package ShopApp.Model;

import java.util.Objects;
import ShopApp.Model.ProductTools.*;

public class UserProduct {
    private User user;
    private Productik product;

    public UserProduct(User user, Productik product) {
        this.user = user;
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public Productik getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProduct that = (UserProduct) o;
        return user.equals(that.user) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, product);
    }
}
