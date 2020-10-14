package model;

import java.util.LinkedList;
import java.util.Objects;

// Represent a list of users
public class UserList {

    protected LinkedList<User> userList;

    //EFFECTS: initialize an empty list to contain users
    public UserList() {
        userList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: Insert non-duplicated users with their password and user name to the list
    public void insertUser(User users) {
        userList.add(users);
    }


    //EFFECTS: count the number of users in the list
    public int countUser() {
        return userList.size();
    }

    //EFFECTS: return true if the list has no users, otherwise false
    public boolean isEmpty() {
        if (userList.size() == 0) {
            return true;
        }
        return false;
    }

    //EFFECTS: return the user at ith index of list, null if index exceed the list's size
    public User returnUser(int i) {
        if (userList.size() < i || (userList.size() == 0)) {
            return null;
        }
        return userList.get(i);
    }

    //EFFECTS: check the list whether has already contain the user.
    public boolean containUser(User user) {
        for (User user1 : userList) {
            if (Objects.equals(user1.getUsername(), user.getUsername())
                    && Objects.equals(user1.getPassword(), user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: clear the entire userList
    public void clearUserList() {
        userList.clear();
    }
}


