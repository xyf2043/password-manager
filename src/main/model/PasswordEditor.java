package model;


import java.util.Objects;

// edit user's password and username from search result
public class PasswordEditor extends PasswordSearcher {

    // EFFECTS: Initialize an empty list to keep changed password and user name
    public PasswordEditor() {
        super();
    }


    // REQUIRES: the userList has no duplicated users
    // && the oldPassword/oldUsername exist in the userList
    // MODIFIES: this
    // EFFECTS: substitute old password in the user list
    public void editPassword(String oldPassword, String newPassword, String username) {
        User user = new User(username, newPassword);

        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getUsername(), username)
                    && Objects.equals(userList.get(i).getPassword(), oldPassword)) {
                userList.set(i, user);
            }
        }
    }

    // REQUIRES: the userList has no duplicated users
    // && the oldPassword/oldUsername exist in the userList
    // MODIFIES: this
    // EFFECTS: substitute old username in the user list
    public void editUsername(String oldUsername, String newUsername, String password) {
        User user = new User(newUsername, password);

        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getUsername(), oldUsername)
                    && Objects.equals(userList.get(i).getPassword(), password)) {
                userList.set(i, user);
            }
        }
    }

    // REQUIRES: the userList has no duplicated users
    // && the oldPassword/oldUsername exist in the userList
    // MODIFIES: this
    // EFFECTS: substitute both old username and old password in the user list
    public void editUser(String oldUsername, String newUsername, String oldPassword, String newPassword) {
        User user = new User(newUsername, newPassword);

        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getUsername(), oldUsername)
                    && Objects.equals(userList.get(i).getPassword(), oldPassword)) {
                userList.set(i, user);
            }
        }
    }

    // REQUIRES: the userList has no duplicated users
    // && the oldPassword/oldUsername exist in the userList
    // MODIFIES: this
    // EFFECTS: by oldUsername and oldPassword, delete particular user in the user list
    public void deleteUser(String oldUsername, String oldPassword) {
        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getUsername(), oldUsername)
                    && Objects.equals(userList.get(i).getPassword(), oldPassword)) {
                userList.remove(userList.get(i));
            }
        }
    }



}




