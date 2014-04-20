package dao;

import logic.User;

/**
 * Created by katya on 4/7/14.
 */
public interface UserDAO {
    public User getUser(String login);
    public void addUser(User user);
    public void deleteUser(User user);
}
