package Objects;

public class User {
    private String name;
    private String surName;
    private String login;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
