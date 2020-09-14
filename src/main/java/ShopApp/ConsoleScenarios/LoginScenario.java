package ShopApp.ConsoleScenarios;

import ShopApp.Model.*;
import ShopApp.Model.User;

public class LoginScenario extends BaseConsoleScenario {
    private User user;
    private IShop shop;

    public LoginScenario(User user, IShop shop) {
        this.user = user;
        this.shop = shop;
    }

    public boolean loginOrRegister() {
        while (true) {
            try {
                int answer = getAnswer("Выберите желаемое действие:\n"
                                + "1. Войти в свой кабинет\n"
                                + "2. Зарегистрироваться\n"
                                + "0. Выход",
                        0, 2);
                switch (answer) {
                    case 1:
                        user = login();
                        break;
                    case 2:
                        user = shop.registerUser("", "", "");
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

    private User login() {
        while (true) {
            String login = getAnswer("Введите логин или нажмите enter, чтобы вернуться назад:", true);
            if (login.isEmpty())
                return null;
            String password = getAnswer("Введите пароль:", true);
            User user = shop.getUser(login, password);
            if (user == null)
                continue;
            return user;
        }
    }
}
