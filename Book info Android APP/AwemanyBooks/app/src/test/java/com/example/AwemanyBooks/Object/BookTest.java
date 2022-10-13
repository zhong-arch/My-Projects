package com.example.AwemanyBooks.Object;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    Book book;

    @Before
    public void setUp() throws Exception {
        System.out.println("Starting test BookTest");

        book=new Book("title","author","des","img","cate",
                "isbn",4.0,55555,
                "azprice","azebprice","chprice","chebprice",0);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("Finished test BookTest");
    }

    @Test
    public void getTitle() {
        String s=book.getTitle();
        assertTrue(s.equals("title"));
    }

    @Test
    public void setTitle() {
        String newTitle="newTitle";
        book.setTitle(newTitle);
        assertTrue(book.getTitle().equals(newTitle));
    }

    @Test
    public void getCategory() {
        String s=book.getCategory();
        assertTrue(s.equals("cate"));
    }

    @Test
    public void setCategory() {
        String newCate="newC";
        book.setTitle(newCate);
        assertTrue(book.getTitle().equals(newCate));
    }

    @Test
    public void getAuthor() {
        String s=book.getAuthor();
        assertTrue(s.equals("author"));
    }

    @Test
    public void setAuthor() {
        String s="testAuthor";
        book.setAuthor(s);
        assertTrue(book.getAuthor().equals(s));
    }

    @Test
    public void getDescription() {
        assertTrue(book.getDescription().equals("des"));
    }

    @Test
    public void setDescription() {
        String s="new des";
        book.setDescription(s);
        assertTrue(book.getDescription().equals(s));
    }

    @Test
    public void getImgUrl() {
        String s =book.getImgUrl();
        assertTrue(s.equals("img"));
    }

    @Test
    public void getIsbn() {
        assertTrue(book.getIsbn().equals("isbn"));
    }

    @Test
    public void setImgUrl() {
        String s="newIMG";
        book.setImgUrl(s);
        assertTrue(book.getImgUrl().equals(s));
    }

    @Test
    public void getScore() {
        assertTrue(book.getScore()==4.0);
    }

    @Test
    public void setScore() {
        book.setScore(5.0);
        assertTrue(book.getScore()==5.0);
    }

    @Test
    public void getDrawableResource() {
        assertTrue(book.getDrawableResource()==55555);
    }

    @Test
    public void getAzprice() {
        assertTrue(book.getAzprice().equals("azprice"));
    }

    @Test
    public void getAzebprice() {
        assertTrue(book.getAzebprice().equals("azebprice"));
    }

    @Test
    public void getChprice() {
        assertTrue(book.getChprice().equals("chprice"));
    }

    @Test
    public void getChebprice() {
        assertTrue(book.getChebprice().equals("chebprice"));
    }

    @Test
    public void setDrawableResource() {
        book.setDrawableResource(123);
        assertTrue(book.getDrawableResource()==123);
    }

    @Test
    public void setISBN() {
        book.setISBN("00000");
        assertTrue(book.getIsbn().equals("00000"));
    }
}