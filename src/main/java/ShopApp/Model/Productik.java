package ShopApp.Model;

import ShopApp.Model.User;
import ShopApp.Model.UserProduct;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

// name "Product" is already booked by Java :(
public class Productik {

    private String name;
    private double price;
    private String producer;
    private Set<String> keywords = new TreeSet<>();
    private int numberSells = 0;
    private Function<UserProduct, Double> ratingGetter;


    public Productik(String name, double price, Function<UserProduct, Double> ratingGetter) {
        this.name = name;
        this.price = price;
        this.ratingGetter = ratingGetter;
    }

    public String getName() {
        return name;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public Double getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
    }

    public int getNumberSells() {
        return numberSells;
    }

    public Double getRating(User user) {
        return ratingGetter.apply(new UserProduct(user, this));
    }

    public Double getRatingZeroFromNull2(User user) {
        return ratingGetter.apply(new UserProduct(user, this)) == null
                ? 0d
                : ratingGetter.apply(new UserProduct(user, this));
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void addSell(){
        numberSells++;
    }


    @Override
    public String toString() {
        return name;
    }
}
