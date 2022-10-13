package com.example.AwemanyBooks.Persistence;

import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Object.User;

import java.util.LinkedList;

public class FavouritePersistenceStub implements FavoritePersistence {
    private LinkedList<User> usersList;
    private LinkedList<BookList> favouriteList;

    public FavouritePersistenceStub() {
        this.usersList = new LinkedList<User>();
        this.favouriteList = new LinkedList<BookList>();

        User user = new User("user1","password1");
        BookList bookList = new BookList();
        usersList.add(user);
        favouriteList.add(bookList);

        user = new User("user2","password2");
        bookList = new BookList();
        usersList.add(user);
        favouriteList.add(bookList);

        user = new User("user3","password3");
        bookList = new BookList();
        usersList.add(user);
        favouriteList.add(bookList);
    }
    @Override
    public BookList getBookSequential(String userName) {
        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(userName)){
                return favouriteList.get(i);
            }
        }
        return null;
    }

    @Override
    public void insertBook(String uid, String bid) {
        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(uid)){
                favouriteList.get(i).addBook(new Book(bid, null, null, null, null, null, 0, 0, null, null, null, null,0));
            }
        }
    }

    @Override
    public void updateBook(String uid, String bid) {
        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(uid)){
                int j=0;
                while (j<favouriteList.get(i).size()){
                    if(favouriteList.get(i).get(j).getTitle().equals(bid)){
                        favouriteList.get(i).setBook(j, new Book(bid, null, null, null, null, null, 0, 0, null, null, null, null,0));
                    }
                }
            }
        }
    }

    @Override
    public void deleteBook(String uid, String bid) {
        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(uid)){
                int j=0;
                while (j<favouriteList.get(i).size()){
                    if(favouriteList.get(i).get(j).getTitle().equals(bid)){
                        favouriteList.get(i).removeBook(j);
                    }
                }
            }
        }
    }

    @Override
    public void deleteBook(String uid) {
        for (int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUID().equals(uid)){
                favouriteList.get(i).emptyList();
            }
        }
    }
}
