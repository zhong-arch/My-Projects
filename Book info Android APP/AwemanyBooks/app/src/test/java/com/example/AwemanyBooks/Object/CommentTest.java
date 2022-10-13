package com.example.AwemanyBooks.Object;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CommentTest {
    @Test
    public void test() {
        final Comment comment;


        String userID = "userTest";
        String userPassword = "password";
        String commentContent = "This is comment";
        User user = new User(userID, userPassword);
        double rating = 4.5;

        System.out.println("\nStarting test Comment");

        System.out.println("Finished test Comment");
    }
}
