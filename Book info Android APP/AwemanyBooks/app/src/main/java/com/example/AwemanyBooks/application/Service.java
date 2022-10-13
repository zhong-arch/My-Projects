package com.example.AwemanyBooks.application;
import com.example.AwemanyBooks.Persistence.BookPersistence;
import com.example.AwemanyBooks.Persistence.FavoritePersistence;
import com.example.AwemanyBooks.Persistence.UserHistoryPersistence;
import com.example.AwemanyBooks.Persistence.Userpersistence;
import com.example.AwemanyBooks.Persistence.CommentPersistence;
import com.example.AwemanyBooks.Persistence.hsqldb.FavoritePersistenceHSQLDB;
import com.example.AwemanyBooks.Persistence.hsqldb.UserPersistenceHSQLDB;
import com.example.AwemanyBooks.Persistence.hsqldb.BookPersistenceHSQLDB;
import com.example.AwemanyBooks.Persistence.hsqldb.UserHistoryPersistenceHSQLDB;
import com.example.AwemanyBooks.Persistence.hsqldb.CommentPersistenceHSQLDB;

public class Service {
    private static Userpersistence userpersistence = null;
    private static BookPersistence bookPersistence = null;
    private static UserHistoryPersistence userHistoryPersistence = null;
    private static FavoritePersistence favoritePersistence = null;
    private static CommentPersistence commentPersistence = null;
    private static String currentUser;
    public static synchronized Userpersistence getUserpersistence(){
        if(userpersistence==null){
            userpersistence=new UserPersistenceHSQLDB( Main.getDBPathName() );
        }
        return userpersistence;
    }
    public static synchronized BookPersistence getBookPersistence() {
        if (bookPersistence == null)
        {
            bookPersistence = new BookPersistenceHSQLDB( Main.getDBPathName() );
        }
        return bookPersistence;
    }
    public static synchronized UserHistoryPersistence getUserHistoryPersistence() {
        if (userHistoryPersistence == null)
        {
            userHistoryPersistence = new UserHistoryPersistenceHSQLDB( Main.getDBPathName() );
        }
        return userHistoryPersistence;
    }
    public static synchronized FavoritePersistence getFavoritePersistence(){
        if(favoritePersistence==null){
            favoritePersistence= new FavoritePersistenceHSQLDB(Main.getDBPathName());
        }
        return favoritePersistence;
    }
    public static synchronized CommentPersistence getCommentPersistence(){
        if(commentPersistence==null){
            commentPersistence = new CommentPersistenceHSQLDB(Main.getDBPathName());
        }
        return commentPersistence;
    }
    public static synchronized String getCurrentUser(){
        if(currentUser == null){
            currentUser = "local";
        }
        return currentUser;
    }
    public static synchronized void setCurrentUser( String currUser ){
        currentUser = currUser;
    }
}
