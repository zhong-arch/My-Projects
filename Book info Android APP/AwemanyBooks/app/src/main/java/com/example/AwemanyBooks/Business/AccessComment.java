package com.example.AwemanyBooks.Business;


import com.example.AwemanyBooks.Persistence.CommentPersistence;
import com.example.AwemanyBooks.application.Service;
import com.example.AwemanyBooks.Object.Comment;
import com.example.AwemanyBooks.Object.Book;

import java.util.ArrayList;
import java.sql.Date;
public class AccessComment {
    private CommentPersistence commentPersistence;
    private AccessBooks accessBooks;
    private BookList bookList;
    private Book book;
    private String bid;
    private CommentList comments;
    private Comment comment;

    public AccessComment( String bid ) {
        commentPersistence = Service.getCommentPersistence();
        this.bid = bid;
        comments = null;
        comment = null;
    }
    public AccessComment(String bid,final CommentPersistence commentPersistence) {
        this( bid );
        this.commentPersistence = commentPersistence;
    }

    public AccessComment(CommentPersistence commentPersistence) {
        this( "bid" );
        this.commentPersistence = commentPersistence;

    }

    public CommentList getComments(){
        CommentList comments=commentPersistence.getComments(bid);
        return comments;
    }

    public Comment getComment(String uid, String bid){
        Comment comment = null;
        if( comments == null ){
            comments = commentPersistence.getComments( bid );
        }
        boolean found = false;
        for( int i = 0; i < comments.size() && !found; i++){
            if( comments.get(i).getUid().equals( uid ) && comments.get(i).getBid().equals( bid ) ){
                found = true;
                comment = comments.get(i);
            }
        }
        return comment;
    }

    public void insertComment(String uid, String bid, String comment, java.sql.Date time, double score) {

        double newScore;
        accessBooks = new AccessBooks();
        bookList = accessBooks.getBooks();
        book = bookList.getBookByID( bid );
        double currScore = book.getScore();
        Comment result = getComment( uid, bid );

        if( result != null ){
            newScore = (currScore * comments.size() - result.getRating() + score)/comments.size();
            updateComment(uid, bid, comment, time, score);
            accessBooks.updateRating( book, newScore );

        }else {
            if( comments.size() == 0 ){
                newScore = score;

            }else {
                newScore = (currScore * comments.size() + score) / (comments.size()+1.0);
            }
            commentPersistence.insertComment(uid, bid, comment, time, score);
            accessBooks.updateRating( book, newScore );
        }
    }

    public void updateComment(String uid, String bid, String comment, java.sql.Date time, double score)
    {
        commentPersistence.updateComment( uid, bid, comment, time, score );
    }

    public void deleteComment(String uid, String bid)
    {
        commentPersistence.deleteComment(uid, bid);
    }
}

