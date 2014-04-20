package dao;


/**
 * Created by katya on 4/7/14.
 */
public class Factory {
    private static UserDAO userDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if( instance == null ) {
            instance = new Factory();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        if( userDAO == null ) {
            userDAO = new UserDAOImplementation();
        }
        return userDAO;
    }
}
