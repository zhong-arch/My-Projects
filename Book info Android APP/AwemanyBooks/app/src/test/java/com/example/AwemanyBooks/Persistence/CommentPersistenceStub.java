package com.example.AwemanyBooks.Persistence;

import com.example.AwemanyBooks.Business.CommentList;
import com.example.AwemanyBooks.Object.Comment;

import java.sql.Date;

public class CommentPersistenceStub implements CommentPersistence{
    private CommentList commentList;

    public CommentPersistenceStub(){
        this.commentList = new CommentList();
        java.util.Date date = new java.util.Date();
        commentList.add(new Comment("uid1", "bid1", "Comment1", date, 1.5));
        commentList.add(new Comment("uid2", "bid2", "Comment2", date, 2.5));
        commentList.add(new Comment("uid3", "bid3", "Comment3", date, 3.5));
        commentList.add(new Comment("uid4", "bid4", "Comment4", date, 4.5));
        commentList.add(new Comment("uid5", "bid5", "Comment5", date, 5.5));
    }

    @Override
    public CommentList getComments(String uname) {
        return commentList;
    }

    @Override
    public void insertComment(String uid, String bid, String comment, Date time, double score) {
        commentList.add(new Comment(uid, bid, comment, time, score));
    }

    @Override
    public void updateComment(String uid, String bid, String comment, Date time, double score) {
        for(int i=0; i<commentList.size(); i++){
            if(commentList.get(i).getUid().equals(uid)){
                commentList.get(i).update(bid, comment, time, score);
            }
        }
    }

    @Override
    public void deleteComment(String uid, String bid) {
        for(int i=0; i<commentList.size(); i++){
            if(commentList.get(i).getUid().equals(uid) && commentList.get(i).getBid().equals(bid)){
                commentList.get(i).setComment(null);
            }
        }
    }

    @Override
    public void deleteComment(String uid) {
        for(int i=0; i<commentList.size(); i++){
            if(commentList.get(i).getUid().equals(uid)){
                commentList.get(i).setComment(null);
            }
        }
    }
}
