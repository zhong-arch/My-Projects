package com.example.AwemanyBooks.Business;

import org.junit.Before;
import org.junit.Test;

import com.example.AwemanyBooks.Object.Book;

import com.example.AwemanyBooks.Persistence.BookPersistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessBooksTest {
    private AccessBooks accessBooks;
    private BookPersistence bookPersistence;

    @Before
    public void setUp() {
        bookPersistence = mock(BookPersistence.class);
        accessBooks = new AccessBooks(bookPersistence);
    }

    @Test
    public void test()
    {
        final Book book;
        book = new Book("The Alchemist", "Paulo Coelho", "Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and soul-stirring wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried near the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself king, and an alchemist, all of whom point Santiago in the direction of his quest. No one knows what the treasure is, or if Santiago will be able to surmount the obstacles in his path. But what starts out as a journey to find worldly goods turns into a discovery of the treasure found within. Lush, evocative, and deeply humane, the story of Santiago is an eternal testament to the transforming power of our dreams and the importance of listening to our hearts.",
                "ImgUrl", "Literary", "10001", 5, 2131165277, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);

        System.out.println("\nStarting test AccessBooks");
        final BookList books = new BookList();
        books.addBook( new Book("The Alchemist", "Paulo Coelho", "Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and soul-stirring wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried near the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself king, and an alchemist, all of whom point Santiago in the direction of his quest. No one knows what the treasure is, or if Santiago will be able to surmount the obstacles in his path. But what starts out as a journey to find worldly goods turns into a discovery of the treasure found within. Lush, evocative, and deeply humane, the story of Santiago is an eternal testament to the transforming power of our dreams and the importance of listening to our hearts.",
                "ImgUrl", "Literary", "10001", 5, 2131165277, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0));
        when(bookPersistence.getBookSequential()).thenReturn(books);
        assertTrue(books.size()>0);
        assertNotNull(book);
        assertTrue(book.getTitle().equals(books.get(0).getTitle()));
        assertTrue(book.getAuthor().equals(books.get(0).getAuthor()));
        assertTrue(book.getDescription().equals(book.getDescription()));
        assertTrue(book.getCategory().equals(books.get(0).getCategory()));
        assertTrue(book.getAzprice().equals(books.get(0).getAzprice()));
        assertTrue(book.getAzebprice().equals(books.get(0).getAzebprice()));
        assertTrue(book.getChprice().equals(books.get(0).getChprice()));
        assertTrue(book.getChebprice().equals(books.get(0).getChebprice()));
        assertTrue(book.getImgUrl().equals(books.get(0).getImgUrl()));
        assertTrue(book.getIsbn().equals(books.get(0).getIsbn()));
        assertTrue(book.getScore() == books.get(0).getScore());
        assertTrue(book.getViews() == books.get(0).getViews());

        //verify(bookPersistence).getBookSequential();

        System.out.println("Finished test AccessBooks");
    }



}
