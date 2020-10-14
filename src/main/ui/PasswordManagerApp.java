package ui;

import model.PasswordEditor;
import model.User;

import java.util.Objects;
import java.util.Scanner;

public class PasswordManagerApp {
    private PasswordEditor userList;
    private Scanner input;
    private String username;
    private String password;
    private String oldName;
    private String newName;
    private String oldPass;
    private String newPass;
    private String key;
    private static final int MAX_LENGTH = 20;

    // Source Reference/Attribute:
    // the interface format of PasswordManagerApp consults
    // the interface format of TellerApp Project.


    // EFFECTS:run the PasswordManagerApplication
    public PasswordManagerApp() {
        runManager();
    }

    // MODIFIES:this
    // EFFECTS:processes user's input
    public void runManager() {
        boolean processing = true;
        String userCommand = null;

        initialize();

        while (processing) {
            displayMenu();
            userCommand = input.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("q")) {
                processing = false;
            } else {
                processUserCommand(userCommand);
            }
        }

        System.out.println("\nQuit App...");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processUserCommand(String command) {
        if (command.equals("s")) {
            doSearch();
        } else if (command.equals("e")) {
            doEdit();
        } else if (command.equals("a")) {
            doAdd();
        } else if (command.equals("d")) {
            doDelete();
        } else if (command.equals("c")) {
            doClear();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes inputs
    private void initialize() {
        input = new Scanner(System.in);
        userList = new PasswordEditor();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nPlease Select from:");
        System.out.println("\ts -> search user");
        System.out.println("\ta -> add user");
        System.out.println("\td -> delete user");
        System.out.println("\te -> edit user");
        System.out.println("\tc -> clear whole user list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts an process of adding user name and password
    private void doAdd() {
        System.out.println("Enter username: ");
        username = input.next();
        System.out.println("Enter password: ");
        password = input.next();
        User user = new User(username, password);

        if (userList.containUser(user)) {
            System.out.println("\tthe user has existed in the list, please add a different user!");
            doAdd();
        } else if (password.length() > MAX_LENGTH || username.length() > MAX_LENGTH) {
            System.out.println("\tPlease shorten your password or username, max 20 characters");
            doAdd();
        } else {
            userList.insertUser(user);
            System.out.println("\tUser Add Success!");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts an process of delete selected user
    private void doDelete() {
        System.out.println("Enter username: ");
        username = input.next();
        System.out.println("Enter password: ");
        password = input.next();
        User user = new User(username, password);

        if (!(userList.containUser(user))) {
            System.out.println("\tthe user is not in the list, please try again!");
        } else {
            userList.deleteUser(username, password);
            System.out.println("\tUser Delete Success!");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts an process of search particular users
    private void doSearch() {
        System.out.print("Enter username, enter null if you forget: ");
        username = input.next();
        System.out.print("Enter password, enter null if you forget: ");
        password = input.next();
        User user = new User(username, password);
        if (Objects.equals(username, "null") && Objects.equals(password, "null")) {
            System.out.println("\tWe would provide the whole list of your password and username!");
            printWholeList();
        } else if (Objects.equals(username, "null") || Objects.equals(password, "null")) {
            System.out.println("\tHere is your possible password and username. Please Check!");
            printSearchList();
        } else if (userList.containUser(user)) {
            System.out.println("\tHere is your password and username. Please Check!");
            printSearchList();
        } else {
            System.out.println("\tBoth username and password do not exist in list! Stop Search and Return to Menu...");
        }
    }


    // MODIFIES: this
    // EFFECTS: conduct a process of editing username and passwords
    private void doEdit() {
        System.out.println("Enter old username, please use search function if you forget: ");
        oldName = input.next();
        System.out.println("Enter old password, please use search function if you forget: ");
        oldPass = input.next();
        User user = new User(oldName, oldPass);
        if (userList.isEmpty() || !(userList.containUser(user))) {
            System.out.println("The List is empty, or User does not exist! Return to Menu... ");
        } else {
            doChangeInfo();
        }
    }

    // MODIFIES: this
    // EFFECTS: change user's password and username
    private void doChangeInfo() {
        System.out.println("Enter 'u' to edit username; Enter 'p' to edit password; Enter 'both' to edit both: ");
        key = input.next();
        if (Objects.equals(key, "u")) {
            System.out.println("Enter new username: ");
            newName = input.next();
            userList.editUsername(oldName, newName, oldPass);
            System.out.println("Edit Success!");
        } else if (Objects.equals(key, "p")) {
            System.out.println("Enter new password: ");
            newPass = input.next();
            userList.editPassword(oldPass, newPass, oldName);
            System.out.println("Edit Success!");
        } else if (Objects.equals(key, "both")) {
            System.out.println("Enter new username: ");
            newName = input.next();
            System.out.println("Enter new password: ");
            newPass = input.next();
            userList.editUser(oldName, newName, oldPass, newPass);
            System.out.println("Edit Success!");
        } else {
            System.out.println("command is invalid! ");
            doEdit();
        }
    }

    // MODIFIES: this
    // EFFECTS: clear the whole userList
    private void doClear() {
        System.out.println("Are you sure to clear your whole list? ");
        System.out.println("Press ‘1’ to clear, Press '0' to stop clear ");
        key = input.next();
        if (Objects.equals(key, "1")) {
            userList.clearUserList();
            System.out.println("clear list success! ");
        } else if (Objects.equals(key, "0")) {
            System.out.println("Stop clear, return to menu... ");
        } else {
            System.out.println("Please enter '1' or '0' only ");
        }

    }

    // EFFECTS: print each element from searching list
    private void printSearchList() {
        for (int i = 0; i < userList.countUser(); i++) {
            System.out.println(userList.searchUser(username, password).get(i));
        }
        if (userList.isEmpty()) {
            System.out.println("The List is Empty...");
        }
    }

    // EFFECTS: print each element from whole list
    private void printWholeList() {
        for (int i = 0; i < userList.countUser(); i++) {
            System.out.println(userList.getWholeUserList().get(i));
        }
        if (userList.isEmpty()) {
            System.out.println("The List is Empty...");
        }
    }

}





