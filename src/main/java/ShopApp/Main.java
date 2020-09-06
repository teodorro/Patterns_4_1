package ShopApp;

import ShopApp.ConsoleScenarios.ConsoleScenario;

public class Main{


    public static void main(String[] args) {
        (new ConsoleScenario(new ShopImpl())).start();
    }


}
