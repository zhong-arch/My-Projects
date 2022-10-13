package com.example.AwemanyBooks.Presentation.ViewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;


import com.example.AwemanyBooks.Business.AccessUserHistory;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.application.Service;

public class HistoryViewModel extends AndroidViewModel {
    private AccessUserHistory accessUserHistory;
    private BookList allBooks;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        accessUserHistory = new AccessUserHistory(Service.getCurrentUser());
        allBooks = accessUserHistory.getBooks();
        System.out.println("read from "+Service.getCurrentUser());
        System.out.println("read from "+Service.getCurrentUser());
        System.out.println("read from "+Service.getCurrentUser());

    }
    public void insert(String uid, String bid) {
        accessUserHistory.insertBook(uid, bid);
    }

    public void update(String uid, String bid) {
        accessUserHistory.updateBook(uid, bid);
    }

    public void delete(String uid, String bid) {
        accessUserHistory.deleteBook(uid);
    }

    public BookList getAllBooks() {
        return allBooks;
    }
}