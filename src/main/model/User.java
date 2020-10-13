package model;

// Represent a user
public class User {
      // a user has username and password
    private String username;
    private String password;

    //EFFECTS: Construct an user with given username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // EFFECTS: Represent password with the username together in one line
    public String toLine() {
        return ("user:" + "[" + username + "]" + "; " + "password:" + "[" + password + "]");
    }
}
