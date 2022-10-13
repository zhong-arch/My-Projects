package com.example.AwemanyBooks;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.example.AwemanyBooks.Utils.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FeatureSevenTest {
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    List<Book> bookList;
    List<String> categories;
    List<Integer> categoryIds;
    String username;
    String password;

    @Before
    public void initialize() {
        bookList = new ArrayList<>();
        categories = new ArrayList<>();
        categoryIds = new ArrayList<>();
        username = "user";
        password = "123456";

        bookList.add(new Book("The Alchemist", "Paulo Coelho", "Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and soul-stirring wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried near the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself king, and an alchemist, all of whom point Santiago in the direction of his quest. No one knows what the treasure is, or if Santiago will be able to surmount the obstacles in his path. But what starts out as a journey to find worldly goods turns into a discovery of the treasure found within. Lush, evocative, and deeply humane, the story of Santiago is an eternal testament to the transforming power of our dreams and the importance of listening to our hearts.",
                "ImgUrl", "Literary", "10001", 5, 2131165277, "14 CAD", "11 CAD", "14 CAD", "11 CAD", 0));
        bookList.add(new Book("The Amphibian", "Alexander Belyaev", "The Amphibian will throw you back to a time when skin and deep-sea diving had not yet made the Silent World begin yielding up its secrets on a really big scale, as aqualung and snorkel are doing today, and present to you Alexander Belayev's 1928 prevision of the ocean mastered by mankind.",
                "ImgUrl", "Literary", "10002", 4.13, 2131165279, "20 CAD", "10 CAD", "N/A", "N/A", 0));
        bookList.add(new Book("The Boy in the Stripped Pajamas", "John Boyne", "If you start to read this book, you will go on a journey with a nine-year-old boy named Bruno. (Though this isn't a book for nine-year-olds.) And sooner or later you will arrive with Bruno at a fence. \\n\\n Fences like this exist all over the world. We hop you never have to encounter one.",
                "ImgUrl", "Children", "10003", 4.14, 2131165284, "13 CAD", "11 CAD", "13 CAD", "N/A", 0));
        bookList.add(new Book("A Little Princess", "Frances Hodgson Burnett", "Sara Crewe, an exceptionally intelligent and imaginative student at Miss Minchin's Select Seminary for Young Ladies, is devastated when her adored, indulgent father dies. Now penniless and banished to a room in the attic, Sara is demeaned, abused, and forced to work as a servant. How this resourceful girl's fortunes change again is at the center of A Little Princess, one of the best-loved stories in all of children's literature.",
                "ImgUrl", "Children", "10004", 4.20, 2131165315, "9 CAD", "4 CAD", "5 CAD", "10 CAD", 0));
        bookList.add(new Book("Little Women", "Louise May Alcott", "Generations of readers young and old, male and female, have fallen in love with the March sisters of Louisa May Alcott's most popular and enduring novel, Little Women. Here are talented tomboy and author-to-be Jo, tragically frail Beth, beautiful Meg, and romantic, spoiled Amy, united in their devotion to each other and their struggles to survive in New England during the Civil War.",
                "ImgUrl", "Classic", "10005", 4.07, 2131165316, "7 CAD", "4 CAD", "8 CAD", "2 CAD", 0));
        bookList.add(new Book("The Devil and Miss Prym", "Paulo Coelho", "A stranger arrives at the remote village of Viscos, carrying with him a backpack containing a notebook and eleven gold bars. He comes searching for the answer to a question that torments him: Are human beings, in essence, good or evil? In welcoming the mysterious foreigner, the whole village becomes an accomplice to his sophisticated plot, which will forever mark their lives.",
                "ImgUrl", "Literary", "10006", 3.60, 2131165316, "12 CAD", "11 CAD", "14 CAD", "N/A", 0));
        bookList.add(new Book("The Steadfast Tin Soldier", "Hans Christian Anderson", "The one-legged Steadfast Tin Soldier braves all sorts of adventures to return to his true love the Ballerina. Fillers Include: Aesop's Fables The Actor and the Farmer, A poem by Robert Louis Stevenson Young Night Thought, The Animal World -- The Dingo and a color me page on the back inside cover.",
                "ImgUrl", "Children", "10007", 4.01, 2131165316, "20 CAD", "15 CAD", "22 CAD", "N/A", 0));
        bookList.add(new Book("The Master and Margarita", "Mikhail Bulgakov", "One hot spring, the devil arrives in Moscow, accompanied by a retinue that includes a beautiful naked witch and an immense talking black cat with a fondness for chess and vodka. The visitors quickly wreak havoc in a city that refuses to believe in either God or Satan. But they also bring peace to two unhappy Muscovites: one is the Master, a writer pilloried for daring to write a novel about Christ and Pontius Pilate; the other is Margarita, who loves the Master so deeply that she is willing literally to go to hell for him. What ensues is a novel of in exhaustible energy, humor, and philosophical depth.",
                "ImgUrl", "Fiction", "10008", 4.30, 2131165316, "10 CAD", "9 CAD", "12 CAD", "15 CAD", 0));
        bookList.add(new Book("The Little Prince", "Antoine de Saint-Exupery", "Moral allegory and spiritual autobiography, The Little Prince is the most translated book in the French language. With a timeless charm it tells the story of a little boy who leaves the safety of his own tiny planet to travel the universe, learning the vagaries of adult behaviour through a series of extraordinary encounters. His personal odyssey culminates in a voyage to Earth and further adventures.",
                "ImgUrl", "Children", "10009", 4.30, 2131165316, "15 CAD", "1 CAD", "10 CAD", "3 CAD", 0));
        bookList.add(new Book("95 Pounds of Hope", "Anna Gavalda", "Every minute Gregory spends in school is torture. The only time he feels really happy is when he's messing around with tools. His Grandpa Leon understands and eventually persuades Gregory to stop blaming other people for his frustration and to apply to technical college. But to do this, Gregory has to take an exam.",
                "ImgUrl", "Teen", "10010", 3.82, 2131165316, "11 CAD", "N/A", "N/A", "N/A", 0));

        categories.add("Literary");
        categories.add("Children");
        categories.add("Teen");
        categories.add("Fiction");

        categoryIds.add(R.id.nav_literary);
        categoryIds.add(R.id.nav_children);
        categoryIds.add(R.id.nav_teen);
        categoryIds.add(R.id.nav_fiction);
    }

    @Test
    public void testUserStory17(){
        openNavFragment(R.id.nav_book_reccommender, "Find New Books");
        checkIfBooksInView(3);
    }

    @Test
    public void testUserStory18(){
        openNavFragment(R.id.nav_book_reccommender, "Find New Books");
        checkIfBooksInView(3);
        clickRandom();
        checkIfBooksInView(3);
    }
    public void checkIfBooksInView(int amountOfBooksToCheck){
        for(int i=0; i< amountOfBooksToCheck; i++) {
            onView(ViewMatchers.withId(R.id.main_recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(
                    i, ViewActions.scrollTo()));
        }
    }
    public void clickRandom(){
        onView(withId(R.id.updateButton)).perform(ViewActions.click());
    }
}
