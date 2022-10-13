package com.example.AwemanyBooks.Persistence;

import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.Business.CommentList;
import com.example.AwemanyBooks.Object.Comment;

import java.util.ArrayList;

public interface CommentPersistence {
//    ArrayList<Comment> getComments(String uname );
    CommentList getComments(String uname);
    void insertComment(String uid, String bid, String comment, java.sql.Date time, double score);
    void updateComment(String uid, String bid, String comment, java.sql.Date time, double score);
    void deleteComment(String uid, String bid);

    void deleteComment(String uid);
}
