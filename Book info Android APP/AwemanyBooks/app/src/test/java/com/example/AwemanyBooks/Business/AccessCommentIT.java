package com.example.AwemanyBooks.Business;

import com.example.AwemanyBooks.Object.Comment;
import com.example.AwemanyBooks.Persistence.CommentPersistence;
import com.example.AwemanyBooks.Persistence.CommentPersistenceStub;
import com.example.AwemanyBooks.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AccessCommentIT {
    private AccessComment accessComment;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        String dbPath = this.tempDB.getAbsolutePath().replace(".script", "");
        final CommentPersistence persistence = new CommentPersistenceStub();
        this.accessComment = new AccessComment(persistence);
    }

    @Test
    public void testListComment() {
        final CommentList comments;
        comments = accessComment.getComments();
        assertNotNull("first sequential course should not be null", comments);
        System.out.println("Finished test AccessCourses");
    }

    @Test
    public void testGetComments() {
        final CommentList commentList = accessComment.getComments();
        assertEquals(5, commentList.size());
    }

    @Test
    public void testDeleteComment() {
        CommentList commentList = accessComment.getComments();

        final Comment comment = commentList.get(0);
        assertEquals(5, commentList.size());
        accessComment.deleteComment(comment.getUid(), comment.getBid());
        commentList = accessComment.getComments();
        assertNull(commentList.get(0).getComment());
    }

    @Test
    public void testInsertBook() {
        java.sql.Date date;
        long millis=System.currentTimeMillis();
        date = new Date(millis);
        //accessComment.insertComment("uid6", "10001", "Comment6", date, 6.5);
        assertEquals(5, accessComment.getComments().size());
    }

    @Test
    public void testUpdateBook() {
        java.sql.Date date = null;
        final Comment newComment = new Comment("uid1", "bid1", "Comment1", date, 1.5);
        accessComment.updateComment("uid1", "bid1", "Comment1", date, 0.5);
        assertTrue(0.5 == accessComment.getComments().get(0).getRating());
        assertTrue("Comment1".equals(accessComment.getComments().get(0).getComment()));
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
