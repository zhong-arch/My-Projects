package com.example.AwemanyBooks.Object;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void test() {
        final User user;


        String userID = "userTest";
        String userPassword = "password";

        user = new User(userID, userPassword);
        System.out.println("\nStarting test User");

        //Test if the user is not a null object and that it contains everything

        assertNotNull(user);
        assertTrue(user.getUID().equals(userID));
        assertTrue(user.getPassword().equals(userPassword));
        assertNotNull(user.getFavorite());
        assertNotNull(user.getViewed());
        Book book = new Book("The Alchemist", "Paulo Coelho", "Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and soul-stirring wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried near the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself king, and an alchemist, all of whom point Santiago in the direction of his quest. No one knows what the treasure is, or if Santiago will be able to surmount the obstacles in his path. But what starts out as a journey to find worldly goods turns into a discovery of the treasure found within. Lush, evocative, and deeply humane, the story of Santiago is an eternal testament to the transforming power of our dreams and the importance of listening to our hearts.",
                "ImgUrl", "Literary", "10001", 5, 2131165277, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);
        user.addToFavorite(book);
        assertTrue(user.getFavorite().get(0).getTitle().equals(book.getTitle()));
        user.addToHistory(book);
        assertTrue(user.getViewed().get(0).getTitle().equals(book.getTitle()));

        //Test the set method

        user.setPassword("newPassword");
        assertTrue(user.checkPassword("newPassword"));

        System.out.println("Finished test User");
    }
}
