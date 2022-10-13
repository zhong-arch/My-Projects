package com.example.AwemanyBooks.Business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Persistence.BookPersistence;
import com.example.AwemanyBooks.Persistence.hsqldb.BookPersistenceHSQLDB;
import com.example.AwemanyBooks.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessBooksIT {

        private AccessBooks accessBooks;
        private File tempDB;


        @Before
        public void setUp() throws IOException {
            this.tempDB = TestUtils.copyDB();
            final BookPersistence persistence = new BookPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
            this.accessBooks = new AccessBooks(persistence);
        }

        @Test
        public void testListBooks() {
            final BookList books;

            books = accessBooks.getBooks();
            assertNotNull("first sequential course should not be null", books);
            assertTrue("The Alchemist".equals(books.get(0).getTitle()));

            System.out.println("Finished test AccessCourses");
        }

        @Test
        public void testGetBooks() {
            final BookList books = accessBooks.getBooks();
            assertEquals(10, books.size());
        }

        @Test
        public void testGetRandomBooks() {
            final BookList books = accessBooks.getRandomBooks();
            assertEquals(3, books.size());
        }

        @Test
        public void testDeleteBook() {
            BookList books = accessBooks.getBooks();

            final Book book = books.get(0);
            assertEquals(10, books.size());
            accessBooks.deleteBook(book);
            books = accessBooks.getBooks();
            assertEquals(9, books.size());
        }

       @Test
        public void testInsertBook() {
            final Book book = new Book("NOT The Alchemist", "Blaw Black", "Description",
                    "ImgUrl", "category", "50001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);

            accessBooks.insertBook(book);
            assertEquals(11, accessBooks.getBooks().size());
        }

        @Test
        public void testUpdateBook() {
            final Book newBook = new Book("The Alchemist", "Blaw Black", "Description",
                    "ImgUrl", "category", "10001", 5, 217, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);

            accessBooks.updateBook(newBook);
            assertEquals(10, accessBooks.getBooks().size());
        }

        @After
        public void tearDown() {
            // reset DB
            this.tempDB.delete();
        }
}
