package com.example.AwemanyBooks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.AwemanyBooks.Business.AccessBooksTest;
import com.example.AwemanyBooks.Business.AccessCommentTest;
import com.example.AwemanyBooks.Business.AccessFavouriteTest;
import com.example.AwemanyBooks.Business.AccessUserHistoryTest;
import com.example.AwemanyBooks.Business.AccessUsersTest;
import com.example.AwemanyBooks.Business.BookListTest;

import com.example.AwemanyBooks.Business.CommentListTest;
import com.example.AwemanyBooks.Object.AuthorTest;
import com.example.AwemanyBooks.Object.CommentTest;
import com.example.AwemanyBooks.Object.BookTest;
import com.example.AwemanyBooks.Object.UserTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessBooksTest.class,
        AccessFavouriteTest.class,
        AccessUserHistoryTest.class,
        BookListTest.class,
        CommentListTest.class,
        AccessUsersTest.class,
        AccessCommentTest.class,
        AuthorTest.class,
        CommentTest.class,
        BookTest.class,
        UserTest.class
})

public class AllTests
{
}
