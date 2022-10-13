package com.example.AwemanyBooks.Business;


import com.example.AwemanyBooks.Persistence.FavoritePersistence;
import com.example.AwemanyBooks.application.Service;
import com.example.AwemanyBooks.Object.Book;

public class AccessFavorite {
    private FavoritePersistence favoritePersistence;
    private String userName;
    private BookList books;
    private Book book;
    private int currentBook;

    public AccessFavorite( String userName ) {
        favoritePersistence = Service.getFavoritePersistence();
        this.userName = userName;
        books = null;
        book = null;
        currentBook = 0;
    }
    public AccessFavorite(String user,final FavoritePersistence favoritePersistence) {
        this(user);
        this.favoritePersistence = favoritePersistence;
    }
    public BookList getBooks() {
        books = favoritePersistence.getBookSequential( userName );
        return books;
    }

    public void insertBook(String uid, String bid)
    {
        favoritePersistence.insertBook(uid, bid);
    }

    public void updateBook(String uid, String bid)
    {
        favoritePersistence.updateBook(uid, bid);
    }

    public void deleteBook(String uid, String bid)
    {
        favoritePersistence.deleteBook(uid, bid);
    }
}

