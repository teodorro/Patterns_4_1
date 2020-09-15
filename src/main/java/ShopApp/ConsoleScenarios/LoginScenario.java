package ShopApp.ConsoleScenarios;

import ShopApp.Model.*;
import ShopApp.Model.User;

public class LoginScenario extends BaseConsoleScenario {
    private User user;
    private ShopUser shop;

    public LoginScenario(User user, ShopUser shop) {
        this.user = user;
        this.shop = shop;
    }

    public boolean loginOrRegister() {
        while (true) {
            try {
                int answer = getAnswer("== Select action:\n"
                                + "1. Log in\n"
                                + "2. Register\n"
                                + "0. Exit",
                        0, 2);
                switch (answer) {
                    case 1:
                        user = login();
                        break;
                    case 2:
                        user = registerUser();
                        break;
                    default:
                        return false;
                }
                if (user != null)
                    return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private User registerUser() {
        String login;
        while (true) {
            login = getAnswer("Write login:", true);
            String finalLogin = login;
            if (shop.getUsers().stream().map(x -> x.getLogin()).noneMatch(y -> y.equals(finalLogin)))
                break;
            else
                System.out.println("User with this login already exists. Choose another one");
        }
        String password = getAnswer("Write password:", true);
        String username = getAnswer("Write username:", true);
        return shop.registerUser(login, password, username);
    }

    private User login() {
        while (true) {
            String login = getAnswer("Write login or press enter to return back:", false);
            if (login.isEmpty())
                return null;
            String password = getAnswer("Write password:", true);
            User user = shop.getUser(login, password);
            if (user == null)
                continue;
            return user;
        }
    }
}
