import model.UserList;
import org.junit.jupiter.api.Test;
import persistence.PasswordReader;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestPasswordReader extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        PasswordReader reader = new PasswordReader("./data/7777.json");
        try {
            UserList userList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyUserList() {
        PasswordReader reader = new PasswordReader("./data/testReaderEmptyUserList.json");
        try {
            UserList userList = reader.read();
            assertEquals(0, userList.countUser());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalUserList() {
        PasswordReader reader = new PasswordReader("./data/testReaderGeneralUserList.json");
        try {
            UserList userList;
            userList = reader.read();
            assertEquals(2, userList.countUser());
            checkUser("678", "999", userList.returnUser(0));
            checkUser("6788","9995",userList.returnUser(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }





}
