package com.example.AwemanyBooks.Object;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthorTest {

    @Test
    public void test() {
        final Author author;


        String authorName = "Anna";
        String authorLastName = "Gavalda";

        author = new Author(authorName, authorLastName);
        System.out.println("\nStarting test Author");

        //Test if the author is not a null object and that it contains everything

        assertNotNull(author);
        assertTrue(author.getName().equals(authorName + " " + authorLastName));

        Book book = new Book("95 Pounds of Hope", "Anna Gavalda", "Every minute Gregory spends in school is torture. The only time he feels really happy is when he's messing around with tools. His Grandpa Leon understands and eventually persuades Gregory to stop blaming other people for his frustration and to apply to technical college. But to do this, Gregory has to take an exam.",
                "ImgUrl", "Teen", "10010", 3.82,123, "11 CAD", "N/A", "N/A", "N/A",0);

        author.addBook(book);
        assertTrue(author.getBooks().get(0).getTitle().equals(book.getTitle()));

        System.out.println("Finished test Author");
    }
}
