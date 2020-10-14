import model.PasswordEditor;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPasswordEditor {
    PasswordEditor userList;

    @BeforeEach
    public void runBefore() {
        userList = new PasswordEditor();
    }

    @Test
    public void testConstructor() {
        assertTrue(userList.isEmpty());

    }

    @Test
    public void testPasswordEditor() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList.insertUser(user);
        }
        userList.editPassword("1","0","2");
        User user = new User("2","0");
        assertTrue(userList.containUser(user));
    }

    @Test
    public void testUsernameEditor() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList.insertUser(user);
        }
        userList.editUsername("3","4","2");
        User user = new User("4","2");
        assertTrue(userList.containUser(user));
    }

    @Test
    public void testUserEditor() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList.insertUser(user);
        }
        userList.editUser("3","4","2","10");
        User user = new User("4","10");
        assertTrue(userList.containUser(user));
    }

    @Test
    public void testDeleteUser(){
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList.insertUser(user);
        }
        User user = new User("2","1");
        userList.deleteUser("2","1");
        assertEquals(9, userList.countUser());
        assertFalse(userList.containUser(user));

    }



}
