package com.example.AwemanyBooks.Business;


import com.example.AwemanyBooks.Persistence.UserHistoryPersistence;
import com.example.AwemanyBooks.application.Service;
import com.example.AwemanyBooks.Object.Book;

public class AccessUserHistory {
    private UserHistoryPersistence userHistoryPersistence;
    private String userName;
    private BookList books;
    private Book book;
    private int currentBook;

    public AccessUserHistory( String userName ) {
        userHistoryPersistence = Service.getUserHistoryPersistence();
        this.userName = userName;
        books = null;
        book = null;
        currentBook = 0;
    }

    public AccessUserHistory(String user, UserHistoryPersistence persistence) {
        this(user);
        this.userHistoryPersistence = persistence;
    }

    public BookList getBooks() {

        books = userHistoryPersistence.getBookSequential( userName );
        return books;
    }
    public void insertBook(String uid, String bid)
    {
        userHistoryPersistence.insertBook(uid, bid);
    }

    public void updateBook(String uid, String bid)
    {
        userHistoryPersistence.updateBook(uid, bid);
    }

    public void deleteBook(String uid)
    {
        userHistoryPersistence.deleteBook(uid);
    }
}

