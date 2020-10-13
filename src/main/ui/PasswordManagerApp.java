package ui;

import model.PasswordEditor;
import model.User;

import java.util.Objects;
import java.util.Scanner;

public class PasswordManagerApp {
    private PasswordEditor userlist;
    private Scanner input;
    private String username;
    private String password;
    private String oldname;
    private String newname;
    private String oldpass;
    private String newpass;
    private String key;

    //EFFECTS:run the PasswordManagerApplication
    public PasswordManagerApp() {
        runManager();
    }

    //MODIFIES:this
    //EFFECTS:processes user input
    public void runManager() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nQuit App...");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            doSearch();
        } else if (command.equals("e")) {
            doEdit();
        } else if (command.equals("a")) {
            doAdd();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes inputs
    private void initialize() {
        input = new Scanner(System.in);
        userlist = new PasswordEditor();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> search");
        System.out.println("\ta -> add password");
        System.out.println("\te -> edit");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts an process of adding user name and password
    private void doAdd() {
        System.out.print("Enter username: ");
        String username = input.next();
        System.out.print("Enter password: ");
        String password = input.next();
        User user = new User(username, password);

        if (userlist.containUser(user)) {
            System.out.println("\tthe user has existed in the list, please add a different user!");
            doAdd();
        } else {
            userlist.insertUser(user);
            System.out.println("\tAdd Success!");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts an process of search particular users
    private void doSearch() {
        System.out.print("Enter username, enter null if you forget: ");
        username = input.next();
        System.out.print("Enter password, enter null if you forget: ");
        password = input.next();
        if (Objects.equals(username, "null") && Objects.equals(password, "null")) {
            System.out.println("\tWe would provide the whole list of your password and username!");
            printWholeList();
        } else if (Objects.equals(username, "null") || Objects.equals(password, "null")) {
            System.out.println("\tHere is your possible password and username. Please Check!");
            printSearchList();
        } else {
            System.out.println("\tHere is your password and username. Please Check!");
            printSearchList();
        }
    }


    // MODIFIES: this
    // EFFECTS: conduct a process of editing username and passwords
    private void doEdit() {
        System.out.println("Enter old username, please use search function if you forget: ");
        oldname = input.next();
        System.out.println("Enter old password, please use search function if you forget: ");
        oldpass = input.next();
        User user = new User(oldname, oldpass);
        if (userlist.isEmpty()) {
            System.out.println("The List is empty! Return to Menu... ");
        } else if (!(userlist.containUser(user))) {
            System.out.println("User does not exist! Please re-enter password and username correctly! ");
            doEdit();
        } else {
            doChange();
        }
    }

    // MODIFIES: this
    // EFFECTS: change user's password and username
    private void doChange() {
        System.out.println("Enter 'u' to edit username; Enter 'p' to edit password; Enter 'both' to edit both: ");
        key = input.next();
        if (Objects.equals(key, "u")) {
            System.out.println("Enter new username: ");
            newname = input.next();
            userlist.editUsername(oldname, newname, oldpass);
            System.out.println("Edit Success!");
        } else if (Objects.equals(key, "p")) {
            System.out.println("Enter new password: ");
            newpass = input.next();
            userlist.editPassword(oldpass, newpass, oldname);
            System.out.println("Edit Success!");
        } else if (Objects.equals(key, "both")) {
            System.out.println("Enter new username: ");
            newname = input.next();
            System.out.println("Enter new password: ");
            newpass = input.next();
            userlist.editUser(oldname, newname, oldpass, newpass);
            System.out.println("Edit Success!");
        } else {
            System.out.println("command is invalid! ");
            doEdit();
        }
    }

    // EFFECTS: print each element from searching list
    private void printSearchList() {
        for (int i = 0; i < userlist.countUser(); i++) {
            System.out.println(userlist.searchUser(username, password).get(i));
        }
    }

    // EFFECTS: print each element from whole list
    private void printWholeList() {
        for (int i = 0; i < userlist.countUser(); i++) {
            System.out.println(userlist.getWholeUserList().get(i));
        }
    }

}





