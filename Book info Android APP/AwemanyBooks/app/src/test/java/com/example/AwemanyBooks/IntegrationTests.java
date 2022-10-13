package com.example.AwemanyBooks;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.AwemanyBooks.Business.AccessBooksIT;
import com.example.AwemanyBooks.Business.AccessCommentIT;
import com.example.AwemanyBooks.Business.AccessFavouriteIT;
import com.example.AwemanyBooks.Business.AccessUserHistoryIT;
import com.example.AwemanyBooks.Business.AccessUsersIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessBooksIT.class,
        AccessFavouriteIT.class,
        AccessUserHistoryIT.class,
        AccessUsersIT.class,
        AccessCommentIT.class
})
public class IntegrationTests {

}
