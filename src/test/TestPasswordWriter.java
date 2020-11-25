import model.DuplicatedUserException;
import model.User;
import model.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.PasswordReader;
import persistence.PasswordWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestPasswordWriter extends JsonTest {

    @BeforeEach
    void runBefore() {
            User user = new User("678", "999");
            UserList userList = new UserList();
        try {
            userList.insertUser(user);
        } catch (DuplicatedUserException e) {
            System.out.println("User has already in list");
        }
    }

    @Test
    void testWriterInvalidFile() {
        try {
            PasswordWriter writer = new PasswordWriter("./data\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyUserList() {
        try {
            UserList userList = new UserList();
            PasswordWriter writer = new PasswordWriter("./data/testWriterEmptyUserList.json");
            writer.open();
            writer.write(userList);
            writer.close();

            PasswordReader reader = new PasswordReader("./data/testWriterEmptyUserList.json");
            userList = reader.read();
            assertEquals(0, userList.countUser());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalUserList() {
        try {
            User user = new User("678", "999");
            User user2 = new User("6788", "9995");
            UserList userList = new UserList();
            try {
                userList.insertUser(user);
                userList.insertUser(user2);
            } catch (DuplicatedUserException e) {
                System.out.println("User has already in list");
            }
            PasswordWriter writer = new PasswordWriter("./data/testWriterGeneralUserList.json");
            writer.open();
            writer.write(userList);
            writer.close();

            PasswordReader reader = new PasswordReader("./data/testWriterGeneralUserList.json");
            userList = reader.read();
            assertEquals(2, userList.countUser());
            assertEquals("678",userList.returnUser(0).getUsername());
            assertEquals("999",userList.returnUser(0).getPassword());
            checkUser("6788","9995",userList.returnUser(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
