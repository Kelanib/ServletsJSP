package beans;

import java.beans.*;
import java.io.Serializable;

public class beanLogin implements Serializable {

    public beanLogin() {
    }

    public boolean checkLogin(String login, String password) {
        if (login == null) {
            return false;
        }
        if (password == null) {
            return false;
        }
        if (login.trim().isEmpty()) {
            return false;
        }
        if (password.trim().isEmpty()) {
            return false;
        }

        if (login.equals("admin")) {
            if (password.equals("root")) {
                return true;
            }
        }

        return false;
    }
}
