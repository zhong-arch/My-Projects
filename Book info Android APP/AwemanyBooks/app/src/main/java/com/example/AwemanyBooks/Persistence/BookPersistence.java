package com.example.AwemanyBooks.Persistence;
import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Business.BookList;

public interface BookPersistence {
    BookList getBookSequential();
    BookList getBooksByCategory(String cg);
    BookList getBooksTopViews();
    Book insertBook(Book currentBook);
    Book updateBook(Book currentBook);
    void updateRating(Book currentBook, double newScore);
    void deleteBook(Book currentBook);
    BookList search(String key);
}

