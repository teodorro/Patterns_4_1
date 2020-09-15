package ShopApp;

import ShopApp.Db.ShopDbTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestShop {
    @Test
    public void test1(){
//        Object db = null;
        ShopDbTest db = new ShopDbTest().Initialize();
        Assertions.assertNotNull(db);
    }
}
