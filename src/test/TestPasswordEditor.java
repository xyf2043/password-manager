import model.PasswordEditor;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPasswordEditor {
    PasswordEditor userlist;

    @BeforeEach
    public void runBefore() {
        userlist = new PasswordEditor();
    }

    @Test
    public void testConstructor() {
        assertTrue(userlist.isEmpty());

    }

    @Test
    public void testPasswordEditor() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        userlist.editPassword("1","0","2");
        User user = new User("2","0");
        assertTrue(userlist.containUser(user));
    }

    @Test
    public void testUsernameEditor() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        userlist.editUsername("3","4","2");
        User user = new User("4","2");
        assertTrue(userlist.containUser(user));
    }

    @Test
    public void testUserEditor() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        userlist.editUser("3","4","2","10");
        User user = new User("4","10");
        assertTrue(userlist.containUser(user));
    }



}
