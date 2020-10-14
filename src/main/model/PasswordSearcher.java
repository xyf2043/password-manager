package model;

import ui.PasswordManagerApp;

import java.util.LinkedList;
import java.util.Objects;

// Search particular users from user list
public class PasswordSearcher extends UserList {

    //EFFECTS: initialize an empty list to contain search results
    public PasswordSearcher() {
        super();
    }


    //REQUIRES:the user list has no duplicate users
    //EFFECTS:use either password or user name to search users, return a list
    // of possible outcomes in ToLine form
    public LinkedList searchUser(String username, String password) {
        LinkedList userinfo = new LinkedList<>();
        for (User user : userlist) {
            if (Objects.equals(user.getUsername(), username) || Objects.equals(user.getPassword(), password)) {
                userinfo.add(user.toLine());
            }
        }
        return userinfo;
    }

    //REQUIRES:the user list has no duplicate users
    //EFFECT: get the whole list of your password and username
    // (if you forget both of them and cannot use search function)
    public LinkedList getWholeUserList() {
        LinkedList userinfo = new LinkedList<>();
        for (User user : userlist) {
            userinfo.add(user.toLine());
        }
        return userinfo;
    }

}

