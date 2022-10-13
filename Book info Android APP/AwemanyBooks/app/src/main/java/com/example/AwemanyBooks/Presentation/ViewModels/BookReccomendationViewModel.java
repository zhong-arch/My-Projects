package com.example.AwemanyBooks.Presentation.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.AwemanyBooks.Business.AccessBooks;
import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Business.BookList;


public class BookReccomendationViewModel extends AndroidViewModel {

    private AccessBooks accessBooks;
    private BookList allBooks;

    public BookReccomendationViewModel(@NonNull Application application) {
        super(application);
        accessBooks = new AccessBooks();
        allBooks = accessBooks.getRandomBooks();
    }

    public void insert(Book book) {
        accessBooks.insertBook(book);
    }

    public void update() {
        allBooks = accessBooks.getRandomBooks();
    }

    public void delete(Book book) {
        accessBooks.deleteBook(book);
    }

    public BookList getAllBooks() {
        return allBooks;
    }
}