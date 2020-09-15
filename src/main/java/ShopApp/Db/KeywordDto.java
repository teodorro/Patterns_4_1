package ShopApp.Db;

import java.util.Set;

public class KeywordDto {
    private String name;
    private Set<Integer> productsId;

    public KeywordDto(String name, Set<Integer> productsId) {
        this.name = name;
        this.productsId = productsId;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getProductsId() {
        return productsId;
    }

    @Override
    public String toString() {
        return "KeywordDto{" +
                "name='" + name + '\'' +
                ", productsId=" + productsId +
                '}';
    }
}
