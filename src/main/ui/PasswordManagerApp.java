package ui;

import model.PasswordEditor;
import model.User;
import persistence.PasswordReader;
import persistence.PasswordWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class PasswordManagerApp extends JFrame {
    private PasswordEditor userList;
    private String username;
    private String password;
    private String oldName;
    private String newName;
    private String oldPass;
    private String newPass;
    private PasswordWriter passwordWriter;
    private PasswordReader passwordReader;
    private static final int MAX_LENGTH = 20;
    private static final String JSON_ADDRESS = "./data/UserList.json";
    private JFrame firstmenu;
    private JFrame mainmenu;
    private JFrame frame2;
    private JFrame frame3;
    private JFrame frame4;
    private JTextField tf;
    private JTextField tf2;
    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HEIGHT = 400;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 30;
    private String string;

    // Source Reference/Attribute:
    // the interface format of PasswordManagerApp consults
    // the interface format of TellerApp Project.


    // Construct the main window
    // EFFECTS:run the PasswordManagerApplication
    public PasswordManagerApp() throws FileNotFoundException {
        super("Password Manager");
        passwordWriter = new PasswordWriter(JSON_ADDRESS);
        passwordReader = new PasswordReader(JSON_ADDRESS);
        startWithLoad();
    }

    // MODIFIES: this
    // EFFECTS: saves the (modified) userList to file
    private void saveUserList() {
        try {
            passwordWriter.open();
            passwordWriter.write(userList);
            passwordWriter.close();
            string = "Saved Modified userList to " + JSON_ADDRESS;
        } catch (FileNotFoundException e) {
            string = "Unable to write to file: " + JSON_ADDRESS;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads userList from file
    private void loadUserList() {
        try {
            userList = passwordReader.read();
            string = "Load userList from " + JSON_ADDRESS;
        } catch (IOException e) {
            string = "Unable to read from file: " + JSON_ADDRESS;
        }
    }

    // MODIFIES: this
    // EFFECTS: print each element from searching list
    private void printSearchList() {
        setFrame4();
        JButton b = new JButton("Return to MainMenu");
        frame4.add(b);
        b.setBounds(500, 900, 200, 30);
        for (int i = 0; i < userList.searchUser(username, password).size(); i++) {
            Panel p = new Panel();
            p.setBounds(200, 50 + 30 * i, 700, 30);
            String s = userList.searchUser(username, password).get(i);
            JLabel label = new JLabel(s);
            p.add(label);
            frame4.add(p);
        }
        if (userList.isEmpty()) {
            remindEmptyList();
        }
        b.addActionListener(e -> {
            frame4.setVisible(false);
            frame4.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: print each element from whole list
    private void printWholeList() {
        setFrame4();
        JButton b = new JButton("Return to MainMenu");
        frame4.add(b);
        b.setBounds(500, 900, 200, 30);
        for (int i = 0; i < userList.countUser(); i++) {
            Panel p = new Panel();
            p.setBounds(200, 50 + 30 * i, 700, 30);
            String s = userList.getWholeUserList().get(i);
            JLabel label = new JLabel(s);
            p.add(label);
            frame4.add(p);
        }
        if (userList.isEmpty()) {
            remindEmptyList();
        }
        b.addActionListener(e -> {
            frame4.setVisible(false);
            frame4.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: remind user the search list is empty
    private void remindEmptyList() {
        Panel p = new Panel();
        p.setBounds(500, 500, 100, 30);
        JLabel label = new JLabel("The List is Empty...");
        p.add(label);
        frame4.add(p);
    }

    // MODIFIES: this
    // EFFECTS: set/reset Frame4 to display the search list result
    private void setFrame4() {
        frame4 = new JFrame("Search Result");
        frame4.setSize(1000, 1000);
        frame4.setLayout(null);
        frame4.setVisible(true);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: an helper to construct the first load reminder menu
    public void startWithLoad() {
        playRemindSound("windows_7_startup.wav");
        firstmenu = new JFrame("Password Manager");
        firstmenu.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        firstmenu.setLayout(null);
        firstmenu.setVisible(true);
        firstmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadOrSkip();
        clickLoad();
        afterClickSkip();
    }

    // MODIFIES: this
    // EFFECTS: construct a panel for firstmenu
    private void loadOrSkip() {
        JPanel panel = new JPanel();
        panel.setBounds(50, 52, 200, 30);
        JLabel label = new JLabel("Load saved file or Skip?");
        panel.add(label);
        firstmenu.add(panel);
    }


    // MODIFIES: this
    // EFFECTS: an helper to remind user that load is successful
    private void clickLoad() {
        JButton b = new JButton("Load");
        b.setBounds(50, 150, 80, 30);
        b.addActionListener(e -> {
                    firstmenu.setVisible(false);
                    firstmenu.dispose();
                    loadPanel();
                }
        );
        firstmenu.add(b);
    }

    // MODIFIES: this
    // EFFECTS: go to the main menu when clicking 'Skip'
    private void afterClickSkip() {
        JButton c = new JButton("Skip");
        c.setBounds(170, 150, 80, 30);
        c.addActionListener(e -> {
            firstmenu.setVisible(false);
            firstmenu.dispose();
            userList = new PasswordEditor();
            goToMainMenu();
        });
        firstmenu.add(c);
    }

    // EFFECTS: return to main menu
    private void goToMainMenu() {
        mainmenu = new JFrame("Main Menu");
        mainmenu.setVisible(true);
        mainmenu.setSize(400, 400);
        mainmenu.setLayout(null);
        mainmenu.setVisible(true);
        mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        afterClickAdd();
        afterClickSearch();
        afterClickEdit();
        afterClickLoad();
        afterClickSave();
        afterClickClear();
        afterClickDelete();
        afterClickQuit();
        afterClickUserManual();
    }

    // MODIFIES: this
    // EFFECTS: display the add user Info menu
    private void afterClickAdd() {
        JButton c = new JButton("Add");
        c.setBounds(SCREEN_WIDTH / 2, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
        c.addActionListener(e -> {
            tf = new JTextField();
            tf.setBounds(175, 52, 150, 30);
            tf2 = new JTextField();
            tf2.setBounds(175, 104, 150, 30);
            frame2 = new JFrame("Add User Info");
            frame2.setVisible(true);
            frame2.setSize(400, 400);
            frame2.setLayout(null);
            frame2.add(tf);
            frame2.add(tf2);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainmenu.setVisible(false);
            mainmenu.dispose();
            twoPanelForAdd();
            submitAndStore();
        });
        mainmenu.add(c);
    }

    // MODIFIES: this
    // EFFECTS: a helper that constructs 'username' and 'password' panel beside text
    private void twoPanelForAdd() {
        JPanel panel = new JPanel();
        panel.setBounds(100, 52, 70, 30);
        JLabel label = new JLabel("Username");
        panel.add(label);
        frame2.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setBounds(100, 104, 70, 30);
        JLabel label2 = new JLabel("Password");
        panel2.add(label2);
        frame2.add(panel2);
    }

    // MODIFIES: this
    // EFFECTS: submit user data and store them in the user list
    private void submitAndStore() {
        JButton c = new JButton("Submit");
        c.setBounds(200, 200, 80, 30);
        frame2.add(c);
        c.addActionListener(e -> {
            username = tf.getText();
            password = tf2.getText();
            User user = new User(username, password);
            if (userList.containUser(user)) {
                playRemindSound("windows_10_notify.wav");
                remindDuplicateUser();
            } else if (password.length() > MAX_LENGTH || username.length() > MAX_LENGTH) {
                playRemindSound("windows_10_notify.wav");
                remindExceedLength();
            } else {
                playRemindSound("windows_xp.wav");
                userList.insertUser(user);
                saveUserList();
                remindSuccessPanel();
            }
            frame2.setVisible(false);
            frame2.dispose();
        });
    }

    // MODIFIES: this
    // EFFECTS: remind user adding is success
    private void remindSuccessPanel() {
        JFrame frame = new JFrame("Success!");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 170, 30);
        JLabel label = new JLabel("Operation Success!");
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: remind user the current user is duplicated
    private void remindDuplicateUser() {
        JFrame frame = new JFrame("Retry!");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 300, 30);
        JLabel label = new JLabel("Duplicated User! Return to main menu...");
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: remind user that the current username and password is too long
    private void remindExceedLength() {
        JFrame frame = new JFrame("Retry!");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(10, 100, 350, 30);
        JLabel label = new JLabel("Username or Password is too long! Return to main menu...");
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: a helper to the main menu that use a search function
    private void afterClickSearch() {
        JButton c = new JButton("Search");
        c.setBounds(SCREEN_WIDTH / 2, 80, BUTTON_WIDTH, BUTTON_HEIGHT);
        c.addActionListener(e -> {
            tf = new JTextField();
            tf.setBounds(175, 52, 150, 30);
            tf2 = new JTextField();
            tf2.setBounds(175, 104, 150, 30);
            frame3 = new JFrame("Search User");
            frame3.setVisible(true);
            frame3.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            frame3.setLayout(null);
            frame3.add(tf);
            frame3.add(tf2);
            mainmenu.setVisible(false);
            mainmenu.dispose();
            twoPanelForSearch();
        });
        mainmenu.add(c);
    }

    // MODIFIES: this
    // EFFECTS: a helper that constructs 'username' and 'password' TextField
    private void twoPanelForSearch() {
        addNameAndPassword();
        JPanel title = new JPanel();
        title.setBounds(70, 25, 300, 30);
        JLabel label3 = new JLabel("Enter Password and Username to Search");
        title.add(label3);
        frame3.add(title);
        JPanel title2 = new JPanel();
        title2.setBounds(70, 250, 300, 30);
        JLabel label4 = new JLabel("Enter null if you forget one of them");
        title2.add(label4);
        frame3.add(title2);
        searchAndDisplay();
    }

    // MODIFIES: this
    // EFFECTS: Construct two TextField to enter username and password
    private void addNameAndPassword() {
        JPanel panel = new JPanel();
        panel.setBounds(100, 52, 70, 30);
        JLabel label = new JLabel("Username");
        panel.add(label);
        frame3.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setBounds(100, 104, 70, 30);
        JLabel label2 = new JLabel("Password");
        panel2.add(label2);
        frame3.add(panel2);
    }


    // MODIFIES: this
    // EFFECTS: display the search result
    private void searchAndDisplay() {
        JButton c = new JButton("Search");
        c.setBounds(200, 200, 80, 30);
        frame3.add(c);
        c.addActionListener(e -> {
            frame3.setVisible(false);
            frame3.dispose();
            username = tf.getText();
            password = tf2.getText();
            User user = new User(username, password);
            if (Objects.equals(username, "null") && Objects.equals(password, "null")) {
                playRemindSound("windows_xp.wav");
                wholeList();
            } else if (Objects.equals(username, "null") || Objects.equals(password, "null")) {
                playRemindSound("windows_xp.wav");
                searchList();
            } else if (userList.containUser(user)) {
                playRemindSound("windows_xp.wav");
                searchList();
            } else {
                playRemindSound("windows_10_notify.wav");
                remindNonExistUser();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: remind user the current user does not exist
    private void remindNonExistUser() {
        JFrame frame = new JFrame("Warning");
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 300, 30);
        JLabel label = new JLabel("\tUsername or password do not exist in list!");
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: print a whole list for user
    private void wholeList() {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(20, 150, 450, 30);
        JLabel label = new JLabel("We would provide the whole list of your password and username!");
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            printWholeList();
            frame.setVisible(false);
            frame.dispose();
        });
    }

    // MODIFIES: this
    // EFFECTS: print a search list for user
    private void searchList() {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 300, 30);
        JLabel label = new JLabel("Here is your password and username. Please Check!");
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            printSearchList();
        });
    }

    // EFFECTS: remind the user with the different sound
    public void playRemindSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error : play sound.");
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: an helper for main menu when users click 'Edit' button
    public void afterClickEdit() {
        JButton c = new JButton("Edit");
        c.setBounds(SCREEN_WIDTH / 2, 110, BUTTON_WIDTH, BUTTON_HEIGHT);
        c.addActionListener(e -> {
            setFrame3();
            mainmenu.setVisible(false);
            mainmenu.dispose();
            panelForEdit();
        });
        mainmenu.add(c);
    }

    // MODIFIES: this
    // EFFECTS: display the 'edit' menu
    private void panelForEdit() {
        JPanel panel = new JPanel();
        panel.setBounds(80, 52, 90, 30);
        JLabel label = new JLabel("Old Username");
        panel.add(label);
        frame3.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setBounds(80, 104, 90, 30);
        JLabel label2 = new JLabel("Old Password");
        panel2.add(label2);
        frame3.add(panel2);
        JPanel title = new JPanel();
        title.setBounds(70, 25, 300, 30);
        JLabel label3 = new JLabel("Enter Old Password and Old Username to Edit");
        title.add(label3);
        frame3.add(title);
        JPanel title2 = new JPanel();
        title2.setBounds(70, 250, 300, 30);
        enterNewUser();
    }


    // MODIFIES: this
    // EFFECTS: construct an interface for user to enter new username and password
    private void otherPanelForEdit() {
        setFrame3();
        JPanel panel = new JPanel();
        panel.setBounds(80, 52, 90, 30);
        JLabel label = new JLabel("New Username");
        panel.add(label);
        frame3.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setBounds(80, 104, 90, 30);
        JLabel label2 = new JLabel("New Password");
        panel2.add(label2);
        frame3.add(panel2);
        JPanel title = new JPanel();
        title.setBounds(70, 25, 300, 30);
        JLabel label3 = new JLabel("Enter New Password and New Username");
        title.add(label3);
        frame3.add(title);
        JPanel title2 = new JPanel();
        title2.setBounds(70, 250, 300, 30);
        finishEditing();
    }

    // MODIFIES: this
    // EFFECTS: get the new user from textField to userList
    private void enterNewUser() {
        JButton c = new JButton("Next");
        c.setBounds(200, 200, 80, 30);
        frame3.add(c);
        c.addActionListener(e -> {
            oldName = tf.getText();
            oldPass = tf2.getText();
            User user = new User(oldName, oldPass);
            if (userList.isEmpty() || !(userList.containUser(user))) {
                playRemindSound("windows_10_notify.wav");
                frame3.setVisible(false);
                frame3.dispose();
                remindNonExistUser();
            } else {
                frame3.setVisible(false);
                frame3.dispose();
                otherPanelForEdit();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: set/reset frame3 to to display 'Edit user' frame
    private void setFrame3() {
        tf = new JTextField();
        tf.setBounds(175, 52, 150, 30);
        tf2 = new JTextField();
        tf2.setBounds(175, 104, 150, 30);
        frame3 = new JFrame("Edit User");
        frame3.setVisible(true);
        frame3.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame3.setLayout(null);
        frame3.add(tf);
        frame3.add(tf2);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // MODIFIES: this
    // EFFECTS: change the user in userList, and remind users editing is successful
    private void finishEditing() {
        JButton c = new JButton("Edit");
        c.setBounds(200, 200, 80, 30);
        frame3.add(c);
        c.addActionListener(e -> {
            playRemindSound("windows_xp.wav");
            newName = tf.getText();
            newPass = tf2.getText();
            userList.editUser(oldName, newName, oldPass, newPass);
            frame3.setVisible(false);
            frame3.dispose();
            remindSuccessPanel();
        });
    }

    // MODIFIES: this
    // EFFECTS: indicate whether the user has successfully load or not
    private void loadPanel() {
        loadUserList();
        JFrame frame = new JFrame("Loading...");
        frame.setSize(700, SCREEN_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 500, 30);
        JLabel label = new JLabel(string);
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(300, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: an helper to remind user that load is successful.
    private void afterClickLoad() {
        JButton b = new JButton("Load");
        b.setBounds(SCREEN_WIDTH / 2, 140, BUTTON_WIDTH, BUTTON_HEIGHT);
        b.addActionListener(e -> {
            mainmenu.setVisible(false);
            mainmenu.dispose();
            playRemindSound("windows_xp_notify.wav");
            loadPanel();
        });
        mainmenu.add(b);
    }

    // MODIFIES: this
    // EFFECTS: an helper to show whether the user has save successful or not
    private void afterClickSave() {
        JButton b = new JButton("Save");
        b.setBounds(SCREEN_WIDTH / 2, 170, BUTTON_WIDTH, BUTTON_HEIGHT);
        b.addActionListener(e -> {
            mainmenu.setVisible(false);
            mainmenu.dispose();
            playRemindSound("windows_xp_notify.wav");
            savePanel();
        });
        mainmenu.add(b);
    }

    // EFFECTS: indicate whether the user has successfully save or not
    private void savePanel() {
        saveUserList();
        JFrame frame = new JFrame("Saving...");
        frame.setSize(700, SCREEN_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 500, 30);
        JLabel label = new JLabel(string);
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(300, 300, 80, 30);
        c.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: indicate after clicking the clear button
    private void afterClickClear() {
        JButton b = new JButton("Clear");
        b.setBounds(SCREEN_WIDTH / 2, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        b.addActionListener(e -> {
            mainmenu.setVisible(false);
            mainmenu.dispose();
            clickClear();
        });
        mainmenu.add(b);
    }

    // MODIFIES: this
    // EFFECTS: let user to continue clear their user data
    private void clickClear() {
        setFrame2();
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 200, 30);
        JLabel label = new JLabel("Sure? Continue to Clear?");
        panel.add(label);
        frame2.add(panel);
        playRemindSound("windows_xp_notify.wav");
        addButtonForClear();
    }

    // MODIFIES: this
    // EFFECTS: set/reset frame 2 to to display 'Sure?' frame
    private void setFrame2() {
        frame2 = new JFrame("Sure?");
        frame2.setVisible(true);
        frame2.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame2.setLayout(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // MODIFIES: this
    // EFFECT: a helper for clear function to add 'Yes'(Clear) 'No'(Not Clear) buttons
    private void addButtonForClear() {
        JButton c = new JButton("No");
        c.setBounds(50, 150, 80, 30);
        frame2.add(c);
        JButton b = new JButton("Yes");
        b.setBounds(70, 150, 80, 30);
        frame2.add(b);
        c.setBounds(200, 150, 80, 30);
        c.addActionListener(e -> {
            frame2.setVisible(false);
            frame2.dispose();
            goToMainMenu();
        });
        b.addActionListener(e -> {
            userList.clearUserList();
            frame2.setVisible(false);
            frame2.dispose();
            goToMainMenu();
        });
    }

    // MODIFIES: this
    // EFFECTS: a helper to show the Delete function in the main menu
    private void afterClickDelete() {
        JButton b = new JButton("Delete");
        b.setBounds(SCREEN_WIDTH / 2, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        b.addActionListener(e -> {
            mainmenu.setVisible(false);
            mainmenu.dispose();
            setFrame3();
            frame3.setTitle("Delete User");
            addNameAndPassword();
            deleteAndDisplay();
        });
        mainmenu.add(b);
    }


    // MODIFIES: this
    // EFFECTS: show users that delete is successful or not
    private void deleteAndDisplay() {
        JButton c = new JButton("Delete");
        c.setBounds(200, 200, 80, 30);
        frame3.add(c);
        c.addActionListener(e -> {
            frame3.setVisible(false);
            frame3.dispose();
            username = tf.getText();
            password = tf2.getText();
            User user = new User(username, password);
            if (userList.containUser(user)) {
                userList.deleteUser(username, password);
                playRemindSound("windows_xp.wav");
                remindSuccessPanel();
            } else {
                playRemindSound("windows_10_notify.wav");
                remindNonExistUser();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: quit the app
    private void afterClickQuit() {
        JButton b = new JButton("Quit");
        b.setBounds(SCREEN_WIDTH / 2, 260, BUTTON_WIDTH, BUTTON_HEIGHT);
        b.addActionListener(e -> {
            mainmenu.setVisible(false);
            mainmenu.dispose();
            setFrame2();
            saveOrLeave();
            playRemindSound("windows_xp_shutdown.wav");
            decideSave();
            decideLeave();
        });
        mainmenu.add(b);
    }

    // MODIFIES: this
    // EFFECTS: before terminate the program, save
    private void decideSave() {
        JButton b = new JButton("Save");
        b.setBounds(50, 150, 80, 30);
        b.addActionListener(e -> {
            frame2.setVisible(false);
            frame2.dispose();
            savePanel2();
        });
        frame2.add(b);
    }

    // MODIFIES: this
    // EFFECTS: save and terminate the program
    private void savePanel2() {
        saveUserList();
        JFrame frame = new JFrame("Saving...");
        frame.setSize(700, SCREEN_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 500, 30);
        JLabel label = new JLabel(string);
        panel.add(label);
        frame.add(panel);
        JButton c = new JButton("OK");
        frame.add(c);
        c.setBounds(300, 300, 80, 30);
        c.addActionListener(e -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
    }

    // MODIFIES: this
    // EFFECTS: terminate the program directly
    private void decideLeave() {
        JButton b = new JButton("Leave");
        b.setBounds(250, 150, 80, 30);
        b.addActionListener(e -> {
            frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
        });
        frame2.add(b);
    }

    // MODIFIES: this
    // EFFECTS: construct a panel with 'Save' and 'Leave' option
    private void saveOrLeave() {
        JPanel panel = new JPanel();
        panel.setBounds(70, 52, 250, 30);
        JLabel label = new JLabel("Save current file or Leave?");
        panel.add(label);
        frame2.add(panel);
    }


    // MODIFIES: this
    // EFFECTS: construct a user manual to provide instructions
    private void afterClickUserManual() {
        JButton b = new JButton("User Manual");
        b.setBounds(20, 150, 180, 60);
        b.addActionListener(e -> {
            mainmenu.setVisible(false);
            mainmenu.dispose();
            setFrame2();
            frame2.setTitle("Manual");
            addManualPanels();
        });
        mainmenu.add(b);
    }

    // MODIFIES: this
    // EFFECTS: a helper to add manual panels in user manual
    private void addManualPanels() {
        JPanel panel = new JPanel();
        panel.setBounds(50, 52, 300, 30);
        JLabel label = new JLabel("Save -> Save current userList");
        panel.add(label);
        frame2.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setBounds(50, 82, 300, 30);
        JLabel label2 = new JLabel("Add -> Add user to userList");
        panel2.add(label2);
        frame2.add(panel2);
        JPanel panel3 = new JPanel();
        panel3.setBounds(50, 112, 300, 30);
        JLabel label3 = new JLabel("Search -> Search users from userList");
        panel3.add(label3);
        frame2.add(panel3);
        JPanel panel4 = new JPanel();
        panel4.setBounds(50, 142, 300, 30);
        JLabel label4 = new JLabel("Edit -> Edit a user from userList");
        panel4.add(label4);
        frame2.add(panel4);
        addMorePanels();
    }


    // MODIFIES: this
    // EFFECTS: a helper to add more manual panels
    private void addMorePanels() {
        JPanel panel5 = new JPanel();
        panel5.setBounds(50, 172, 300, 30);
        JLabel label5 = new JLabel("Load -> Load userList from the saved file");
        panel5.add(label5);
        frame2.add(panel5);
        JPanel panel6 = new JPanel();
        panel6.setBounds(50, 202, 300, 30);
        JLabel label6 = new JLabel("Clear -> clear all users from userList");
        panel6.add(label6);
        frame2.add(panel6);
        JPanel panel7 = new JPanel();
        panel7.setBounds(50, 232, 300, 30);
        JLabel label7 = new JLabel("Delete -> delete a selected user from userList");
        panel7.add(label7);
        frame2.add(panel7);
        JPanel panel8 = new JPanel();
        panel8.setBounds(50, 262, 300, 30);
        JLabel label8 = new JLabel("Quit -> quit app");
        panel8.add(label8);
        frame2.add(panel8);
        addReturnButton();
    }

    // MODIFIES: this
    // EFFECTS: a helper to create JFrame to return to the main menu
    private void addReturnButton() {
        JButton c = new JButton("OK");
        frame2.add(c);
        c.setBounds(150, 300, 80, 30);
        c.addActionListener(e -> {
            frame2.setVisible(false);
            frame2.dispose();
            goToMainMenu();
        });
    }

}









