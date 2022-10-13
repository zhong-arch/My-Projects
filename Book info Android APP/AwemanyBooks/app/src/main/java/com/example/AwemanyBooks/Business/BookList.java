package com.example.AwemanyBooks.Business;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.AwemanyBooks.Object.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookList implements Parcelable {

    private List<Book> bookList;

    //constructors
    public BookList() {
        bookList = new ArrayList<>();
    }



    protected BookList(Parcel in) {
        bookList = in.createTypedArrayList(Book.CREATOR);
    }

    public static final Creator<BookList> CREATOR = new Creator<BookList>() {
        @Override
        public BookList createFromParcel(Parcel in) {
            return new BookList(in);
        }

        @Override
        public BookList[] newArray(int size) {
            return new BookList[size];
        }
    };

    //GETTERS
    public Book get(int position){
        return bookList.get(position);
    }
    public int size(){
        return bookList.size();
    }

    //SETTERS
    public void setBook(int index, Book newBook){
        bookList.set(index,newBook);
    }

    //OTHER METHODS RELATED TO BOOK
    public void addBook(Book newBook){
        bookList.add(newBook);
    }
    public void removeBook(int index){
        bookList.remove(index);
    }

    //OTHER METHODS WITH THE LIST
    public void emptyList(){
        bookList.clear();
    }

    public void sort() {
        Collections.sort(bookList, Book.BookViews);
    }



    public boolean searchBookByID( String bid ){

        boolean found = false;
        String target = bid;

        for( int i = 0; i < bookList.size()&&!found; i++ ){
            if( bookList.get(i).getIsbn().equals( target )){
                found = true;
            }
        }
        return found;
    }
    public Book getBookByID( String bid ){

        Book book = null;
        boolean found = false;
        String target = bid;

        for( int i = 0; i < bookList.size()&&!found; i++ ){
            if( bookList.get(i).getIsbn().equals( target )){
                found = true;
                book = bookList.get(i);
            }
        }
        return book;
    }
    //Searches for the input in the title, author, category, isbn
    public BookList find(String string){
        BookList result = new BookList();
        if(string != null) {
            string=string.toLowerCase();

                for (int i = 0; i < bookList.size(); i++) {
                    String bookInfo = bookList.get(i).getTitle().toLowerCase() + " ";
                    bookInfo = bookInfo + bookList.get(i).getAuthor().toLowerCase() + " ";
                    bookInfo = bookInfo + bookList.get(i).getCategory().toLowerCase() + " ";
                    bookInfo = bookInfo + bookList.get(i).getIsbn().toLowerCase() + " ";

                    if (bookInfo.contains(string)) {
                        result.addBook(bookList.get(i));
                    }
                }

        }
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(bookList);
    }
}
