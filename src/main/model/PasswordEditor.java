package model;


import java.util.Objects;

// edit user's password and username from search result
public class PasswordEditor extends PasswordSearcher {

    // EFFECTS: Initialize an empty list to keep changed password and user name
    public PasswordEditor() {
        super();
    }


    // REQUIRES: the userlist has no duplicated users
    // && the oldpassword/oldusername exist in the userlist
    // MODIFIES: this
    // EFFECTS: substitute old password in the user list
    public void editPassword(String oldpassword, String newpassword, String username) {
        User user = new User(username, newpassword);

        for (int i = 0; i < userlist.size(); i++) {
            if (Objects.equals(userlist.get(i).getUsername(), username)
                    && Objects.equals(userlist.get(i).getPassword(), oldpassword)) {
                userlist.set(i, user);
            }
        }
    }

    // REQUIRES: the userlist has no duplicated users
    // && the oldpassword/oldusername exist in the userlist
    // MODIFIES: this
    // EFFECTS: substitute old username in the user list
    public void editUsername(String oldusername, String newusername, String password) {
        User user = new User(newusername, password);

        for (int i = 0; i < userlist.size(); i++) {
            if (Objects.equals(userlist.get(i).getUsername(), oldusername)
                    && Objects.equals(userlist.get(i).getPassword(), password)) {
                userlist.set(i, user);
            }
        }
    }

    // REQUIRES: the userlist has no duplicated users
    // && the oldpassword/oldusername exist in the userlist
    // MODIFIES: this
    // EFFECTS: substitute both old username and old password in the user list
    public void editUser(String oldusername, String newusername, String oldpassword, String newpassword) {
        User user = new User(newusername, newpassword);

        for (int i = 0; i < userlist.size(); i++) {
            if (Objects.equals(userlist.get(i).getUsername(), oldusername)
                    && Objects.equals(userlist.get(i).getPassword(), oldpassword)) {
                userlist.set(i, user);
            }
        }
    }

    // REQUIRES: the userlist has no duplicated users
    // && the oldpassword/oldusername exist in the userlist
    // MODIFIES: this
    // EFFECTS: by oldusername and oldpassword, delete particular user in the user list
    public void deleteUser(String oldusername, String oldpassword) {
        for (int i = 0; i < userlist.size(); i++) {
            if (Objects.equals(userlist.get(i).getUsername(), oldusername)
                    && Objects.equals(userlist.get(i).getPassword(), oldpassword)) {
                userlist.remove(userlist.get(i));
            }
        }
    }



}




