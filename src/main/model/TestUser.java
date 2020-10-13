package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;

//Design unit test for User in this class

class TestUser {
    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void runBefore() {
        user1 = new User(null,null);
        user2 = new User("2","1");
        user3 = new User("5", "67/");
    }

    @Test
    public void testConstructor() {
        assertEquals(null, user1.getPassword());
        assertEquals(null, user1.getUsername());
        assertEquals("1", user2.getPassword());
        assertEquals("2", user2.getUsername());
        assertEquals("67/", user3.getPassword());
        assertEquals("5", user3.getUsername());
    }

    @Test
    public void testToLine() {
        assertEquals("user:[2]; password:[1]",user2.toLine());
    }


}
