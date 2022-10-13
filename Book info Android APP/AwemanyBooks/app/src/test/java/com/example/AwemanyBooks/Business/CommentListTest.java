package com.example.AwemanyBooks.Business;

import com.example.AwemanyBooks.Object.Comment;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CommentListTest {
    private CommentList commentList;
    private Comment comment1 = new Comment("first","10001","comment a",new Date(2020,4,10),3.0);
    private Comment comment2 = new Comment("second","10001","comment b",new Date(2020,4,11),4.0);
    private Comment comment3 = new Comment("third","10001","comment c",new Date(2020,4,12),5.0);
    @Before
    public void setup(){
        commentList =new CommentList();
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
    }
    @Test
    public void testGet() {
        Comment c= commentList.get(0);
        boolean result=c.getUid().equals(comment1.getUid());
        assertTrue(result && c.getBid().equals(comment1.getBid()));
        assertEquals(c.getRating(), comment1.getRating(), 0.0);
        assertEquals(c.getComment(), comment1.getComment());
    }
    @Test
    public void testAdd() {
        commentList =new CommentList();
        commentList.add(comment1);
        Comment c= commentList.get(0);
        assertSame(c, comment1);
    }

    @Test
    public void testSize() {
        assertEquals(3, commentList.size());
    }

    @Test
    public void sortByDate() {
        commentList.sortByDate();
        for(int i = 0; i< commentList.size()-1; i++){
            Comment t1,t2;
            t1= commentList.get(i);
            t2= commentList.get(i+1);
            assertTrue(t1.getTime().compareTo(t2.getTime())<=0);
        }
    }

    @Test
    public void sortByDateDes() {
        commentList.sortByDateDes();
        for(int i = 0; i< commentList.size()-1; i++){
            Comment t1,t2;
            t1= commentList.get(i);
            t2= commentList.get(i+1);
            assertTrue(t1.getTime().compareTo(t2.getTime())>=0);
        }
    }

    @Test
    public void sortByRating() {
        commentList.sortByRating();
        for(int i = 0; i< commentList.size()-1; i++){
            Comment t1,t2;
            t1= commentList.get(i);
            t2= commentList.get(i+1);
            assertTrue(t1.getRating()<=t2.getRating());
        }
    }

    @Test
    public void sortByRatingDes() {
        commentList.sortByRatingDes();
        for(int i = 0; i< commentList.size()-1; i++){
            Comment t1,t2;
            t1= commentList.get(i);
            t2= commentList.get(i+1);
            assertTrue(t1.getRating()>=t2.getRating());
        }
    }
}