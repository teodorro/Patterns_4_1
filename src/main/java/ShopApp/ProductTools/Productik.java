package ShopApp.ProductTools;

import ShopApp.IdCreator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;

public class Productik {

    private int id;
    private String name;
    private double price;
    private String producer;
    private Set<String> keywords = new TreeSet<>();
    private int numberSells = 0;
    private int numberRatings = 0;
    private Function<Productik, Double> ratingGetter;
//    private Double rating = null;


    public Productik(String name, double price, Function<Productik, Double> ratingGetter) {
        this.id = IdCreator.getInstance().getNextId();
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

    public int getId() {
        return id;
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

    public Double getRating() { return ratingGetter.apply(this); }
    public Double getRatingZeroFromNull() { return ratingGetter.apply(this) == null ? 0 : ratingGetter.apply(this); }

    public void setProducer(String producer) {
        this.producer = producer;
    }

//    public void setRating(Supplier<Double> getRating) {
//        this.ratingGetter = getRating;
//    }

    public void addSell(){
        numberSells++;
    }

//    public void addRating(Double rating){
//        if (numberSells == 0)
//            throw new IllegalStateException("impossible product state. Setting rating with no sells");
//        if (rating > 0) {
//            numberRatings++;
//            this.rating = numberRatings == 1
//                    ? rating
//                    : (this.rating * (numberRatings - 1) + rating) / numberRatings;
//        } else{
//            numberRatings--;
//            //TODO? need to know old rating!
//        }
//    }


    @Override
    public String toString() {
        return name;
    }
}
