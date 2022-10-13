package com.example.AwemanyBooks.Object;

import java.util.Date;

public class Comment {
    private String uid;
    private String bid;
    private String comment;
    private Date time;
    private double score;

    public Comment( String uid, String bid, String comment, Date time, double score){

        this.uid = uid;
        this.bid = bid;
        this.comment = comment;
        this.time = time;
        this.score = score;
    }

    public String getUid() {
        return uid;
    }

    public String getBid(){
        return bid;
    }

    public String getComment() {
        return comment;
    }

    public double getRating() {
        return score;
    }

    public String getTime(){ return time.toString(); }

    public void update(String bid, String comment, java.sql.Date time, double score){
        this.bid = bid;
        this.comment = comment;
        this.time = time;
        this.score = score;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
