package com.example.AwemanyBooks.Business;


import com.example.AwemanyBooks.application.Service;
import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Persistence.BookPersistence;

import java.util.ArrayList;
import java.util.List;

public class AccessBooks {
    private BookPersistence bookPersistence;
    private BookList books;
    private Book book;
    private int currentBook;

    public AccessBooks() {
        bookPersistence = Service.getBookPersistence();
        books = null;
        book = null;
        currentBook = 0;
    }

    public AccessBooks(final BookPersistence bookPersistence) {
        this();
        this.bookPersistence = bookPersistence;
    }

    public BookList getBooks() {

        books = bookPersistence.getBookSequential();
        return books;
    }

    public BookList getBooksTopViews() {

        books = bookPersistence.getBooksTopViews();
        return books;
    }

    public BookList getRandomBooks(){
        BookList resultList = new BookList();
        BookList allBooks = getBooks();
        List<Integer> numbersPicked = new ArrayList<>();
        boolean foundNewNum = false;
        for (int i = 0; i< 3; i++){
            foundNewNum = false;
            int position = (int) (Math.random() * allBooks.size());
            while(!foundNewNum) {
                if (numbersPicked.contains(position)) {
                    position = (int) (Math.random() * allBooks.size());
                } else {
                    foundNewNum = true;
                    numbersPicked.add(position);
                }
            }
            resultList.addBook(allBooks.get(position));
        }
        return resultList;
    }


    public BookList getBooksByCategory(String cg ) {

        books = bookPersistence.getBooksByCategory( cg );
        return books;
    }
    public Book insertBook(Book currentBook)
    {
        return bookPersistence.insertBook(currentBook);
    }

    public Book updateBook(Book currentBook)
    {
        return bookPersistence.updateBook(currentBook);
    }
    public void updateRating(Book currentBook, double rating){
    bookPersistence.updateRating( currentBook, rating );
    }
    public void deleteBook(Book currentBook)
    {
        bookPersistence.deleteBook(currentBook);
    }
    public BookList search(String key){
        return bookPersistence.search(key);
    }
}

