package dbservice;

import dao.Factory;
import logic.User;

/**
 * Created by katya on 4/7/14.
 */
public class DBService {
    public static User getUser(String login) {
        return Factory.getInstance().getUserDAO().getUser(login);
    }

    public static boolean addUser(User user) {
        if( getUser(user.getLogin()) == null ) {
            Factory.getInstance().getUserDAO().addUser(user);
            return true;
        }
        else {
            return  false;
        }
    }

    public static boolean deleteUser(User user) {
        if( getUser(user.getLogin()) == null ) {
            Factory.getInstance().getUserDAO().deleteUser(user);
            return true;
        }
        else {
            return  false;
        }
    }
}
