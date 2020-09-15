package ShopApp.Model;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

// name "Product" is already booked by Java :(
public class Productik implements Comparable<Productik>{

    private String name;
    private double price;
    private String producer;
    private Set<String> keywords = new TreeSet<>();
    private int numberSells = 0;
    private Function<Productik, Double> ratingAvgGetter;
    private Function<UserProduct, Double> ratingSetByUserGetter;


    public Productik(String name, double price, Function<Productik, Double> ratingAvgGetter, Function<UserProduct, Double> ratingSetByUserGetter) {
        this.name = name;
        this.price = price;
        this.ratingAvgGetter = ratingAvgGetter;
        this.ratingSetByUserGetter = ratingSetByUserGetter;
    }

    public String getName() {
        return name;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords){
        this.keywords = keywords;
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

    public Double getAvgRating() {
        return ratingAvgGetter.apply(this);
    }

    public Double getRatingSetByUser(User user) {
        return ratingSetByUserGetter.apply(new UserProduct(user, this));
    }

    public Double getRatingZeroFromNull2(User user) {
        return ratingSetByUserGetter.apply(new UserProduct(user, this)) == null
                ? 0d
                : ratingSetByUserGetter.apply(new UserProduct(user, this));
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

    @Override
    public int compareTo(Productik productik) {
        return this.name.compareTo(productik.name);
    }
}
