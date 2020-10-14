import model.PasswordSearcher;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TestPasswordSearcher {
    PasswordSearcher userList;
    LinkedList<User> userList2;

    @BeforeEach
    public void runBefore() {
        userList = new PasswordSearcher();
        userList2 = new LinkedList<>();
    }

    @Test
    public void testConstructor() {
        assertTrue(userList.isEmpty());
    }


    @Test
    public void testSearchUserWithEmptySearchList() {
        assertTrue(userList.searchUser("1", "1").isEmpty());
    }


    @Test
    public void testSearchUserWithNoPossibleOutcome() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList.insertUser(user);
        }

        assertEquals(10, userList.countUser());
        assertTrue(userList.searchUser("-1", "-1").isEmpty());

        userList2 = userList.searchUser(null, null);
        assertEquals(0, userList2.size());

    }

    @Test
    public void testSearchUserWithPossibleOutcome() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userList.insertUser(user);
        }
        userList2 = userList.searchUser("1", "1");
        assertEquals(2, userList2.size());

        userList2 = userList.searchUser("-1", "1");
        assertEquals(1, userList2.size());

        userList2 = userList.searchUser("3", null);
        assertEquals(1, userList2.size());

        userList2 = userList.searchUser("10", "9");
        assertEquals(1, userList2.size());

    }


    @Test
    public void testGetWholeUserList() {
        for (int i = 0; i < 3; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i);
            User user = new User(username, password);
            userList.insertUser(user);
        }
        userList2 = userList.getWholeUserList();
        assertEquals(3, userList.countUser());
        assertEquals(3, userList2.size());
        assertEquals("user:[0]; password:[0]", userList2.get(0));
        assertEquals("user:[2]; password:[2]", userList2.get(2));
    }


}


