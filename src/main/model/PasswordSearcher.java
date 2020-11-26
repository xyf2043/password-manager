package model;

import java.util.LinkedList;
import java.util.Objects;

// Search particular users from user list
public class PasswordSearcher extends UserList {

    //EFFECTS: initialize an empty list to contain search results
    public PasswordSearcher() {
        super();
    }


    // EFFECTS: use either password or user name to search users, return a list
    // of possible outcomes in ToLine form
    public LinkedList<String> searchUser(String username, String password) {
        LinkedList userinfo = new LinkedList<>();
        for (User user : userList) {
            if (Objects.equals(user.getUsername(), username) || Objects.equals(user.getPassword(), password)) {
                userinfo.add(user.toLine());
            }
        }
        return userinfo;
    }

    //EFFECT: get the whole list of your password and username
    // (if you forget both of them and cannot use search function)
    public LinkedList<String> getWholeUserList() {
        LinkedList userinfo = new LinkedList<>();
        for (User user : userList) {
            userinfo.add(user.toLine());
        }
        return userinfo;
    }

}

