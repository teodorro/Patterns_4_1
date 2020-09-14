package ShopApp.Db;

public class RatingDto {
    private int userId;
    private int productId;
    private double value;

    public RatingDto(int userId, int productId, double value) {
        this.userId = userId;
        this.productId = productId;
        this.value = value;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public double getValue() {
        return value;
    }
}
