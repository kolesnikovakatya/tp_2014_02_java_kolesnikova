package logic;

/**
 * Created by katya on 4/6/14.
 */
public class User {
    private Long id;
    private String login;
    private String pass;

    public User() {
        login = null;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setId(Long i) {
        id = i;
    }

    public void setLogin(String l) {
        login = l;
    }

    public void setPass(String p) {
        pass = p;
    }

}
