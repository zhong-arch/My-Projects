package com.example.AwemanyBooks;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.DrawerMatchers;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class Utils {
    public static void signUp(String username, String password){
        openNavFragment(R.id.nav_login, "Login");
        onView(withId(R.id.editText2)).perform(ViewActions.click());
        onView(withId(R.id.editText2)).perform(ViewActions.typeText(username),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editText3)).perform(ViewActions.click());
        onView(withId(R.id.editText3)).perform(ViewActions.typeText(password),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button4)).perform(ViewActions.click());
        onView(withText(R.string.signup_success)).check(ViewAssertions.matches(isDisplayed()));
    }

    public static void login(String username, String password){
        openNavFragment(R.id.nav_login, "Login");
        onView(withId(R.id.editText2)).perform(ViewActions.click());
        onView(withId(R.id.editText2)).perform(ViewActions.clearText(), ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editText3)).perform(ViewActions.click());
        onView(withId(R.id.editText3)).perform(ViewActions.clearText(), ViewActions.typeText(password),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button1)).perform(ViewActions.click());
        onView(withText(R.string.login_sucsessful)).check(ViewAssertions.matches(isDisplayed()));
    }

    public static void logOut(){
        openNavFragment(R.id.nav_user_account, "User Account");
        onView(withId(R.id.button5)).perform(ViewActions.click());
        onView(withId(R.id.toolbar)).check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText("Home"))));
    }

    public static void openNavFragment(int id, String title) {
        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open());
        onView(withId(R.id.drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isOpen()));
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(id));
        onView(withId(R.id.toolbar)).check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(title))));
    }

    public static void clickOnBook(int position){
        onView(ViewMatchers.withId(R.id.main_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(
                position, ViewActions.click()));
    }
}
