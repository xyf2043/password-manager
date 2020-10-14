import model.User;
import model.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserList {


    UserList userList1;
    UserList userList2;

    @BeforeEach
    public void runBefore() {
        userList1 = new UserList();
        userList2 = new UserList();
    }

    @Test
    public void testConstructor() {
        assertTrue(userList1.isEmpty());
        assertTrue(userList2.isEmpty());
    }

    @Test
    public void testIsEmpty(){
        UserList userList = new UserList();
        assertTrue(userList.isEmpty());
    }


    @Test
    public void testIsNotEmpty(){
        User user = new User("1", "2");
        userList1.insertUser(user);
        assertFalse(userList1.isEmpty());
    }

    @Test
    public void testCountUser(){
        assertEquals(0, userList1.countUser());
        User user = new User("1", "2");
        userList2.insertUser(user);
        userList2.insertUser(user);
        assertEquals(2, userList2.countUser());
    }

    @Test
    public void testReturnUser(){
        assertEquals(null, userList1.returnUser(5));
        assertEquals(null, userList1.returnUser(0));
        User user = new User("1", "2");
        User user1 = new User("1", "2");
        userList2.insertUser(user);
        assertTrue(Objects.equals(user, userList2.returnUser(0)));
        assertFalse(Objects.equals(user1, userList2.returnUser(0)));
    }


    @Test
    public void testInsertOneUser() {
        User user = new User("1", "2");
        userList1.insertUser(user);
        assertEquals(1, userList1.countUser());
    }

    @Test
    public void testInsertMultipleUser() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList1.insertUser(user);
        }
        assertEquals(10, userList1.countUser());
        assertEquals("9", userList1.returnUser(9).getPassword());
        assertEquals("10", userList1.returnUser(9).getUsername());
    }

    @Test
    public void testContainNoUser() {
        User user = new User("1", "2");
        userList1.insertUser(user);
        User user2 = new User("10", "20");

        assertFalse(userList1.containUser(user2));
    }

    @Test
    public void testContainUser() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList1.insertUser(user);
        }
        assertEquals(10, userList1.countUser());

        User user = new User("2", "1");
        User user1 = new User("7","6");
        assertTrue(userList1.containUser(user));
        assertTrue(userList1.containUser(user1));


    }

    @Test
    public void testClearUserList(){
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList1.insertUser(user);
        }
        userList1.clearUserList();
        assertEquals(0, userList1.countUser());
        assertTrue(userList1.isEmpty());
    }
}
