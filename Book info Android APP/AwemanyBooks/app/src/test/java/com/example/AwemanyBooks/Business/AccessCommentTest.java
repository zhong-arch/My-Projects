package com.example.AwemanyBooks.Business;

import com.example.AwemanyBooks.Object.Comment;
import com.example.AwemanyBooks.Persistence.CommentPersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccessCommentTest {

    private AccessComment accessComment;
    private CommentPersistence commentPersistence;

    @Before
    public void setUp() {
        commentPersistence = mock(CommentPersistence.class);
        accessComment = new AccessComment(commentPersistence);
    }

    @Test
    public void test()
    {
        final Comment comment;
        Date date = new Date();

        comment = new Comment("uid", "bid", "Comment", date, 3.5);

        System.out.println("\nStarting test AccessBooks");
        final CommentList comments = new CommentList();
        comments.add( new Comment("uid", "bid", "Comment", date, 3.5));

        assertTrue(comments.size()>0);
        assertNotNull(comment);
        assertTrue(comment.getBid().equals(comments.get(0).getBid()));
        assertTrue(comment.getComment().equals(comments.get(0).getComment()));
        assertTrue(comment.getUid().equals(comment.getUid()));
        assertTrue(comment.getRating()==comments.get(0).getRating());

        //verify(bookPersistence).getBookSequential();

        System.out.println("Finished test AccessBooks");
    }

}
