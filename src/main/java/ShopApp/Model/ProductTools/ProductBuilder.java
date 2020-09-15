package ShopApp.Model.ProductTools;

import ShopApp.Model.Productik;
import ShopApp.Model.ShopUser;

import java.util.Set;
import java.util.TreeSet;

public class ProductBuilder {
    private String name;
    private Double price;
    private String producer;
    private Set<String> keywords = new TreeSet<>();

    private static ProductBuilder instance;
    public static ProductBuilder getInstance(){
        if (shop == null)
            throw new NullPointerException("Initialize shop first");
        if (instance == null)
            instance = new ProductBuilder();
        return instance;
    }

    private static ShopUser shop;
    public static void setShop(ShopUser shopchik){
        shop = shopchik;
    }

    private ProductBuilder(){}


    public Productik build(){
        if (name == null)
            throw new IllegalStateException("product name was not initialized");
        if (price == 0)
            throw new IllegalStateException("price equals zero");
        Productik product = new Productik(name, price, x -> shop.getAvgRating(x) , x -> shop.getRating(x));
        product.setProducer(producer);
        product.getKeywords().addAll(keywords);
        clear();
        return product;
    }

    private void clear() {
        name = null;
        producer = null;
        price = null;
        keywords.clear();
    }

    public ProductBuilder setProducer(String producer){
        this.producer = producer;
        return this;
    }

    public ProductBuilder addKeyword(String keyword){
        keywords.add(keyword);
        return this;
    }

    public ProductBuilder addKeywords(Set<String> keywords){
        this.keywords.addAll(keywords);
        return this;
    }

    public ProductBuilder setName(String name){
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(double price){
        this.price = price;
        return this;
    }


}
