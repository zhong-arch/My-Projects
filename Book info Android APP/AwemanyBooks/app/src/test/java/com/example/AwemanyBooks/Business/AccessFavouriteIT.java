package com.example.AwemanyBooks.Business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Persistence.FavoritePersistence;
import com.example.AwemanyBooks.Persistence.hsqldb.FavoritePersistenceHSQLDB;
import com.example.AwemanyBooks.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessFavouriteIT {

    private AccessFavorite accessFavorite;
    private File tempDB;
    private String user="username";

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final FavoritePersistence persistence = new FavoritePersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessFavorite = new AccessFavorite(user, persistence);
    }

    @Test
    public void testGetBooks() {
        final BookList books = accessFavorite.getBooks();
        assertEquals(0, books.size());
    }

    @Test
    public void testDeleteBook() {
        final Book book = new Book("NOT The Alchemist", "Blaw Black", "Description",
                "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);
        accessFavorite.insertBook(user, book.getIsbn());
        BookList books = accessFavorite.getBooks();
        assertEquals(1, books.size());
        accessFavorite.deleteBook(user, book.getIsbn());
        books = accessFavorite.getBooks();
        assertEquals(0, books.size());
    }

    @Test
    public void testInsertBook() {
        final Book book = new Book("NOT The Alchemist", "Blaw Black", "Description",
                "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);

        accessFavorite.insertBook(user, book.getIsbn());
        assertEquals(1, accessFavorite.getBooks().size());
    }

    @Test
    public void testUpdateBook() {
        final Book newBook = new Book("The Alchemist", "Blaw Black", "Description",
                "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);
        accessFavorite.insertBook(user, newBook.getIsbn());
        accessFavorite.updateBook(user, newBook.getIsbn());
        assertEquals(1, accessFavorite.getBooks().size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
