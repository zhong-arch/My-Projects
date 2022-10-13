package com.example.AwemanyBooks.Business;

import com.example.AwemanyBooks.Object.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CommentList {
    private ArrayList<Comment> comments;
    public CommentList(){
        comments=new ArrayList<Comment>();
    }
    public void add(Comment c){
        comments.add(c);
    }
    public int size(){
        return comments.size();
    }
    public Comment get(int p){
        return comments.get(p);
    }
    public void sortByDate(){
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
    }
    public void sortByDateDes(){
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return -( o1.getTime().compareTo(o2.getTime()) );
            }
        });
    }
    public void sortByRating(){
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return (int)(o1.getRating()-o2.getRating());
            }
        });
    }
    public void sortByRatingDes(){
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return -( (int)(o1.getRating()-o2.getRating()) );
            }
        });
    }
}
