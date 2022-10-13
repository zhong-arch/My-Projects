package com.example.AwemanyBooks.Business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.example.AwemanyBooks.Object.User;
import com.example.AwemanyBooks.Persistence.Userpersistence;
import com.example.AwemanyBooks.Persistence.hsqldb.UserPersistenceHSQLDB;
import com.example.AwemanyBooks.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessUsersIT {

    private AccessUsers accessUsers;
    private File tempDB;
    private LinkedList<User> users;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final Userpersistence persistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessUsers = new AccessUsers(persistence);
    }


    @Test
    public void testRegisterUser() {
        final User user = new User("user1", "12345");
        assertNotNull(user);
        accessUsers.register("user1", 12345);
        assertTrue(accessUsers.matchRecord("user1",12345));
    }


    @Test
    public void testRemoveUser() {
        final User user = new User("user1", "12345");
        assertNotNull(user);
        accessUsers.register("user1", 12345);
        assertTrue(accessUsers.matchRecord("user1",12345));
        accessUsers.removeUser(user);
        assertTrue(!accessUsers.matchRecord("user1",12345));
    }

    @Test
    public void testCheckDuplicate() {
        final User user = new User("user1", "12345");
        assertNotNull(user);
        final User user1 = new User("user2", "123456");
        assertNotNull(user);
        assertNotNull(user1);

        accessUsers.register("user1", 12345);
        assertTrue(accessUsers.matchRecord("user1",12345));
        assertTrue(!accessUsers.checkDuplicate("user1"));

        accessUsers.register("user2", 123456);
        assertTrue(accessUsers.matchRecord("user2", 123456));
        assertTrue(!accessUsers.checkDuplicate("user2"));


    }

    @Test
    public void testMatchUser() {
        final User user = new User("user1", "12345");
        assertNotNull(user);
        final User user1 = new User("user2", "123456");
        assertNotNull(user);
        assertNotNull(user1);

        accessUsers.register("user1", 12345);
        assertTrue(accessUsers.matchRecord("user1",12345));

        accessUsers.register("user2", 123456);
        assertTrue(accessUsers.matchRecord("user2", 123456));

        accessUsers.removeUser(user);
        assertTrue(!accessUsers.matchRecord("user1",12345));

        accessUsers.removeUser(user1);
        assertTrue(!accessUsers.matchRecord("user2", 123456));
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
