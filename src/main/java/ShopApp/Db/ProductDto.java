package ShopApp.Db;

public class ProductDto {
    private int id;
    private String name;
    private double price;
    private String producer;
    private int numberSells;

    public ProductDto(int id, String name, double price, String producer, int numberSells) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.producer = producer;
        this.numberSells = numberSells;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
    }

    public int getNumberSells() {
        return numberSells;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", producer='" + producer + '\'' +
                ", numberSells=" + numberSells +
                '}';
    }
}
