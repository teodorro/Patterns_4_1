package ShopApp.ProductTools;

import ShopApp.ProductTools.Comparators.ProductIdComparator;
import ShopApp.ProductTools.Comparators.ProductPopularityComparator;
import ShopApp.ProductTools.Comparators.ProductRatingComparator;
import ShopApp.ProductTools.Comparators.ProductRecommendComparator;
import ShopApp.User;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsFilter {
    private Supplier<Set<Productik>> getProducts;
    private Double minPrice = null;
    private Double maxPrice = null;
    private Set<String> producers = new HashSet<>();
    private Set<String> keywords = new HashSet<>();
    private boolean mostPopular;
    private boolean highestRating;


    public ProductsFilter(Supplier<Set<Productik>> getProducts) {
        this.getProducts = getProducts;
    }

    public TreeSet<Productik> filter(){
        Stream<Productik> filteredStream = getProducts.get().stream();
        if (minPrice != null)
            filteredStream = filteredStream.filter(x -> x.getPrice() >= minPrice);
        if (maxPrice != null)
            filteredStream = filteredStream.filter(x -> x.getPrice() <= maxPrice);
        if (producers.size() > 0)
            filteredStream = filteredStream.filter(x -> producers.contains(x.getProducer()));
        if (keywords.size() > 0)
            filteredStream = filteredStream.filter(x -> x.getKeywords().containsAll(keywords));
        Set<Productik> filtered = filteredStream.collect(Collectors.toSet());
        if (highestRating) {
            TreeSet sorted = new TreeSet<Productik>(new ProductRatingComparator());
            sorted.addAll(filtered);
            return sorted;
        }
        if (mostPopular) {
            TreeSet sorted = new TreeSet<Productik>(new ProductPopularityComparator());
            sorted.addAll(filtered);
            return sorted;
        }
        TreeSet sorted = new TreeSet<Productik>(new ProductIdComparator());
        sorted.addAll(filtered);
        return sorted;
    }

    public void clear(){
        minPrice = null;
        maxPrice = null;
        producers.clear();
        keywords.clear();
        mostPopular = false;
        highestRating = false;
    }

    public ProductsFilter setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public ProductsFilter setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public ProductsFilter setMostPopular(boolean mostPopular) {
        this.mostPopular = mostPopular;
        if (mostPopular)
            highestRating = false;
        return this;
    }

    public ProductsFilter setHighestRating(boolean highestRating) {
        this.highestRating = highestRating;
        if (highestRating)
            mostPopular = false;
        return this;
    }

    public TreeSet<Productik> getRecommended(User user, Set<User> allUsers){
        Set<User> otherUsers = allUsers.stream().filter(x -> x != user).collect(Collectors.toSet());
        Set<Productik> marked = user.getRatings().keySet();
        Set<Productik> notMarked = allUsers.stream().flatMap(x -> x.getRatings().keySet().stream()).filter(x -> !marked.contains(x)).collect(Collectors.toSet());
//        Set<Productik> notMarked = getProducts.get().stream().filter(x -> !marked.contains(x)).collect(Collectors.toSet());
        TreeSet<Productik> res = new TreeSet<>(new ProductRecommendComparator().prepare(user, otherUsers));
        res.addAll(notMarked);
        return res;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() { return maxPrice; }

    public Set<String> getProducers() {
        return producers;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public boolean isMostPopular() { return mostPopular; }

    public boolean isHighestRating() { return highestRating; }

}
