package com.example.AwemanyBooks.Presentation.ViewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;


import com.example.AwemanyBooks.Business.AccessBooks;
import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Business.BookList;

public class ChildrenViewModel extends AndroidViewModel {
    private AccessBooks accessBooks;
    private BookList allBooks;

    final private String categoryName = "Children";

    public ChildrenViewModel(@NonNull Application application) {
        super(application);
        accessBooks = new AccessBooks();
        allBooks = accessBooks.getBooksByCategory(categoryName);
    }

    public void insert(Book book) {
        accessBooks.insertBook(book);
    }

    public void update(Book book) {
        accessBooks.updateBook(book);
    }

    public void delete(Book book) {
        accessBooks.deleteBook(book);
    }

    public BookList getAllBooks() {
        return allBooks;
    }
}