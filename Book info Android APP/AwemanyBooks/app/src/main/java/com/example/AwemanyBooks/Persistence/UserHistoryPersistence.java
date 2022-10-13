package com.example.AwemanyBooks.Persistence;
import com.example.AwemanyBooks.Business.BookList;

public interface UserHistoryPersistence {
    BookList getBookSequential( String uname );
    void insertBook(String uid, String bid);
    void updateBook(String uid, String bid);
    void deleteBook(String uid);
}

