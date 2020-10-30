package model;

import org.json.JSONObject;

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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        return json;
    }


}
