package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestUserList {


    UserList userlist1;
    UserList userlist2;

    @BeforeEach
    public void runBefore() {
        userlist1 = new UserList();
        userlist2 = new UserList();
    }

    @Test
    public void testConstructor() {
        assertTrue(userlist1.isEmpty());
        assertTrue(userlist2.isEmpty());
    }

    @Test
    public void testInsertOneUser() {
        User user = new User("1", "2");
        userlist1.insertUser(user);
        assertEquals(1, userlist1.countUser());
    }

    @Test
    public void testInsertMultipleUser() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist1.insertUser(user);
        }
        assertEquals(10, userlist1.countUser());
        assertEquals("9", userlist1.returnUser(9).getPassword());
        assertEquals("10", userlist1.returnUser(9).getUsername());
    }

    @Test
    public void testContainNoUser() {
        User user = new User("1", "2");
        userlist1.insertUser(user);
        User user2 = new User("10", "20");

        assertFalse(userlist1.containUser(user2));
    }

    @Test
    public void testContainUser() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist1.insertUser(user);
        }
        assertEquals(10,userlist1.countUser());

        User user = new User("2", "1");
        User user1 = new User("7","6");
        assertTrue(userlist1.containUser(user));
        assertTrue(userlist1.containUser(user1));


    }
}
