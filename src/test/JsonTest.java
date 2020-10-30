import model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkUser(String username, String password, User user) {
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }
}


