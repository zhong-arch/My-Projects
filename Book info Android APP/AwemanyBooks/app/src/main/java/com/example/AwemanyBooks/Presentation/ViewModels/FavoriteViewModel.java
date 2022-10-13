package com.example.AwemanyBooks.Presentation.ViewModels;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

import com.example.AwemanyBooks.Business.AccessFavorite;

import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.application.Service;


public class FavoriteViewModel extends AndroidViewModel {
    private AccessFavorite accessFavorite;
    private BookList allBooks;


    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        accessFavorite = new AccessFavorite(Service.getCurrentUser());
        allBooks = accessFavorite.getBooks();
    }

    public void insert(String uid, String bid) {
        accessFavorite.insertBook(uid, bid);
    }

    public void update() {
        allBooks = accessFavorite.getBooks();
    }

    public void delete(String uid, String bid) {
        accessFavorite.deleteBook(uid, bid);
    }

    public BookList getAllBooks() {
        return allBooks;
    }
}
