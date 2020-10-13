import model.PasswordSearcher;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TestPasswordSearcher {
    PasswordSearcher userlist;
    LinkedList<User> userlist2;

    @BeforeEach
    public void runBefore() {
        userlist = new PasswordSearcher();
        userlist2 = new LinkedList<>();
    }

    @Test
    public void testConstructor() {
        assertTrue(userlist.isEmpty());
    }


    @Test
    public void testSearchUserWithEmptySearchList() {
        assertTrue(userlist.searchUser("1", "1").isEmpty());
    }

    @Test
    public void testSearchAccurateUserSuccess() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        assertTrue(userlist.searchAccurateUser("1", "0"));
        assertEquals(1,userlist.countUser());
    }

    @Test
    public void testSearchAccurateUserFail() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        assertFalse(userlist.searchAccurateUser(null, null));
        assertFalse(userlist.searchAccurateUser("100", "6"));
        assertFalse(userlist.searchAccurateUser("6", "6"));
    }

    @Test
    public void testSearchUserWithNoPossibleOutcome() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }

        assertEquals(10, userlist.countUser());
        assertTrue(userlist.searchUser("-1", "-1").isEmpty());

        userlist2 = userlist.searchUser(null, null);
        assertEquals(0, userlist2.size());

    }

    @Test
    public void testSearchUserWithPossibleOutcome() {
        for (int i = 0; i < 10; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i + 1);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        userlist2 = userlist.searchUser("1", "1");
        assertEquals(2, userlist2.size());

        userlist2 = userlist.searchUser("-1", "1");
        assertEquals(1, userlist2.size());

        userlist2 = userlist.searchUser("3", null);
        assertEquals(1, userlist2.size());

        userlist2 = userlist.searchUser("10", "9");
        assertEquals(1, userlist2.size());

    }


    @Test
    public void testGetWholeUserList() {
        for (int i = 0; i < 3; i++) {
            String password = Integer.toString(i);
            String username = Integer.toString(i);
            User user = new User(username, password);
            userlist.insertUser(user);
        }
        userlist2 = userlist.getWholeUserList();
        assertEquals(3, userlist.countUser());
        assertEquals(3, userlist2.size());
        assertEquals("user:[0]; password:[0]", userlist2.get(0));
        assertEquals("user:[2]; password:[2]", userlist2.get(2));
    }


}


