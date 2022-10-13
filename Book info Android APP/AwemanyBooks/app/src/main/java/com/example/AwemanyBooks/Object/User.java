package com.example.AwemanyBooks.Object;

import androidx.annotation.Nullable;

import com.example.AwemanyBooks.Business.BookList;

public class User {
    private final String UID;
    private String password;
    private BookList favorite;
    private BookList viewed;
    private final int maxViewedSize=10;
    public User(){
        UID="";
        password="";
        favorite=new BookList();
        viewed=new BookList();
    }

    public String getPassword() {
        return password;
    }

    public BookList getViewed() {
        return viewed;
    }

    public BookList getFavorite() {
        return favorite;
    }

    public String getUID() {
        return UID;
    }

    public User(String id, String pw){
        UID=id;
        password=pw;
        favorite=new BookList();
        viewed=new BookList();
    }
    public boolean checkPassword(String input){
        return input.hashCode()==password.hashCode();
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void addToFavorite(Book book){
        favorite.addBook(book);
    }
    public void removeFromFavorite(Book book){
        //need remove method in BookList
    }
    public void addToHistory(Book book){
        int currentSize=viewed.size();
        if (currentSize<maxViewedSize){
            viewed.addBook(book);
        }
        else{

            viewed.addBook(book);
        }

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof User){
            User t=(User) obj;
            return this.UID.equals(t.UID);
        }
        return false;
    }
}
