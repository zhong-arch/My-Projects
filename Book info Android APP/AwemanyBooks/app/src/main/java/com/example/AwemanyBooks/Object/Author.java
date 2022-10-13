package com.example.AwemanyBooks.Object;

import com.example.AwemanyBooks.Business.BookList;

public class Author {
    private String firstName;
    private String lastName;
    private BookList books;
    public Author(){
        firstName="";
        lastName="";
        books=new BookList();
    }
    public Author(String f,String l){
        firstName=f;
        lastName=l;
        books=new BookList();
    }
    public void addBook(Book book){
        books.addBook(book);
    }
    public BookList getBooks(){
        return books;
    }
    public String getName(){
        return firstName+" "+lastName;
    }

}
