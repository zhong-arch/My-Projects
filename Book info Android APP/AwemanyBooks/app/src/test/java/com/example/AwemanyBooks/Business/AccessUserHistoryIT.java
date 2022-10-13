package com.example.AwemanyBooks.Business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Persistence.UserHistoryPersistence;
import com.example.AwemanyBooks.Persistence.hsqldb.UserHistoryPersistenceHSQLDB;
import com.example.AwemanyBooks.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessUserHistoryIT {

    private AccessUserHistory accessUserHistory;
    private File tempDB;
    private String user = "username";

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final UserHistoryPersistence persistence = new UserHistoryPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessUserHistory = new AccessUserHistory(user, persistence);
    }

    @Test
    public void testListBooks() {
        final BookList books;

        books = accessUserHistory.getBooks();
        assertNotNull("first sequential course should not be null", books);
        assertTrue(0 ==books.size());

    }

    @Test
    public void testGetBooks() {
        final BookList books = accessUserHistory.getBooks();
        assertEquals(0, books.size());
    }

    @Test
    public void testDeleteBook() {
        final Book book = new Book("NOT The Alchemist", "Blaw Black", "Description",
                "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);
        accessUserHistory.insertBook(user, book.getIsbn());
        BookList books = accessUserHistory.getBooks();
        assertEquals(1, books.size());
        accessUserHistory.deleteBook(user);
        books = accessUserHistory.getBooks();
        assertEquals(0, books.size());
    }

    @Test
    public void testInsertBook() {
        final Book book = new Book("NOT The Alchemist", "Blaw Black", "Description",
                "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);

        accessUserHistory.insertBook(user, book.getIsbn());
        assertEquals(1, accessUserHistory.getBooks().size());
    }

    @Test
    public void testUpdateBook() {
        final Book newBook = new Book("The Alchemist", "Blaw Black", "Description",
                "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);
        accessUserHistory.insertBook(user, newBook.getIsbn());
        accessUserHistory.updateBook(user, newBook.getIsbn());
        assertEquals(1, accessUserHistory.getBooks().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
