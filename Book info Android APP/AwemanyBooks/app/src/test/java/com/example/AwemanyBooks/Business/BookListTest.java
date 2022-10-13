package com.example.AwemanyBooks.Business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.R;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class BookListTest {
    BookList bookList = new BookList();
    Book book1=new Book("The Alchemist", "Paulo Coelho", "Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and soul-stirring wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried near the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself king, and an alchemist, all of whom point Santiago in the direction of his quest. No one knows what the treasure is, or if Santiago will be able to surmount the obstacles in his path. But what starts out as a journey to find worldly goods turns into a discovery of the treasure found within. Lush, evocative, and deeply humane, the story of Santiago is an eternal testament to the transforming power of our dreams and the importance of listening to our hearts.",
                           "ImgUrl", "Literary", "10001", 5, R.drawable.alchemist, "14 CAD", "11 CAD", "14 CAD", "11 CAD",0);
    Book book2=new Book("The Boy in the Stripped Pajamas", "John Boyne", "If you start to read this book, you will go on a journey with a nine-year-old boy named Bruno. (Though this isn't a book for nine-year-olds.) And sooner or later you will arrive with Bruno at a fence. \n\n Fences like this exist all over the world. We hop you never have to encounter one.",
                           "ImgUrl", "Children", "10003", 4.14, R.drawable.boy_in_pajamas, "13 CAD", "11 CAD", "13 CAD", "N/A",0);
    Book book3=new Book("A Little Princess", "Frances Hodgson Burnett", "Sara Crewe, an exceptionally intelligent and imaginative student at Miss Minchin's Select Seminary for Young Ladies, is devastated when her adored, indulgent father dies. Now penniless and banished to a room in the attic, Sara is demeaned, abused, and forced to work as a servant. How this resourceful girl's fortunes change again is at the center of A Little Princess, one of the best-loved stories in all of children's literature.",
                           "ImgUrl", "Children", "10004", 4.20, R.drawable.little_princess, "9 CAD", "4 CAD", "5 CAD", "10 CAD",0);
    Book book4=new Book("Little Women", "Louise May Alcott", "Generations of readers young and old, male and female, have fallen in love with the March sisters of Louisa May Alcott’s most popular and enduring novel, Little Women. Here are talented tomboy and author-to-be Jo, tragically frail Beth, beautiful Meg, and romantic, spoiled Amy, united in their devotion to each other and their struggles to survive in New England during the Civil War.",
                           "ImgUrl", "Classic", "10005", 4.07, R.drawable.little_women_book, "7 CAD", "4 CAD", "8 CAD", "2 CAD",0);
    Book book5=new Book("The Devil and Miss Prym", "Paulo Coelho", "A stranger arrives at the remote village of Viscos, carrying with him a backpack containing a notebook and eleven gold bars. He comes searching for the answer to a question that torments him: Are human beings, in essence, good or evil? In welcoming the mysterious foreigner, the whole village becomes an accomplice to his sophisticated plot, which will forever mark their lives.",
                           "ImgUrl", "Literary", "10006", 3.60, R.drawable.the_devil_and_miss_prym, "12 CAD", "11 CAD", "14 CAD", "N/A",0);
    Book book6=new Book("The Steadfast Tin Soldier", "Hans Christian Anderson", "The one-legged Steadfast Tin Soldier braves all sorts of adventures to return to his true love the Ballerina. Fillers Include: Aesop's Fables The Actor and the Farmer, A poem by Robert Louis Stevenson Young Night Thought, The Animal World -- The Dingo and a color me page on the back inside cover.",
                           "ImgUrl", "Children", "10007", 4.01, R.drawable.tin_soldier, "20 CAD", "15 CAD", "22 CAD", "N/A",0);
    Book book7=new Book("The Master and Margarita", "Mikhail Bulgakov", "One hot spring, the devil arrives in Moscow, accompanied by a retinue that includes a beautiful naked witch and an immense talking black cat with a fondness for chess and vodka. The visitors quickly wreak havoc in a city that refuses to believe in either God or Satan. But they also bring peace to two unhappy Muscovites: one is the Master, a writer pilloried for daring to write a novel about Christ and Pontius Pilate; the other is Margarita, who loves the Master so deeply that she is willing literally to go to hell for him. What ensues is a novel of in exhaustible energy, humor, and philosophical depth.",
                           "ImgUrl", "Fiction", "10008", 4.30, R.drawable.the_master_and_margarita, "10 CAD", "9 CAD", "12 CAD", "15 CAD",0);
    Book book8=new Book("The Little Prince", "Antoine de Saint-Exupery", "Moral allegory and spiritual autobiography, The Little Prince is the most translated book in the French language. With a timeless charm it tells the story of a little boy who leaves the safety of his own tiny planet to travel the universe, learning the vagaries of adult behaviour through a series of extraordinary encounters. His personal odyssey culminates in a voyage to Earth and further adventures.",
                           "ImgUrl", "Children", "10009", 4.30, R.drawable.the_little_prince, "15 CAD", "1 CAD", "10 CAD", "3 CAD",0);
    Book book9=new Book("95 Pounds of Hope", "Anna Gavalda", "Every minute Gregory spends in school is torture. The only time he feels really happy is when he's messing around with tools. His Grandpa Leon understands and eventually persuades Gregory to stop blaming other people for his frustration and to apply to technical college. But to do this, Gregory has to take an exam.",
                           "ImgUrl", "Teen", "10010", 3.82, R.drawable.ninety_five_lbs_of_hope, "11 CAD", "N/A", "N/A", "N/A",0);
    Book[]books={book1,book2,book3,book4,book5,book6,book7,book8,book9};





    @Before
    public void setUp() {
        System.out.println("Starting test BookListTest");

        bookList=new BookList();
        bookList.addBook(book1);
        bookList.addBook(book2);
        bookList.addBook(book3);
        bookList.addBook(book4);
        bookList.addBook(book5);
        bookList.addBook(book6);
        bookList.addBook(book7);
        bookList.addBook(book8);
        bookList.addBook(book9);
    }
    @After
    public void endOfTest(){
        System.out.println("Finished test BookListTest");
    }

    @AfterClass
    public static void endOfAllTest(){
        System.out.println("test pass!");
    }

    @Test
    public void size() {
        int size=9;
        assertTrue(bookList.size()==size);
        bookList=new BookList();
        assertTrue(bookList.size()==0);
    }
    @Test
    public void get() {
        int size=bookList.size();
        for (int i=0;i<size;i++){
            Book inList=bookList.get(i);
            Book actual=books[i];
            assertTrue(inList==actual);
        }


    }



    @Test
    public void addBook() {
        Book extra=new Book("title","author","des","img","cate","isbn",4.0,55555,"azprice","azebprice","chprice","chebprice",0);
        bookList.addBook(extra);
        assertTrue(bookList.get(bookList.size()-1)==extra);
        bookList.removeBook(9);

    }

    @Test
    public void testSearchbyID(){
        assertTrue(bookList.searchBookByID("10001"));
        assertFalse(bookList.searchBookByID("11111"));
    }

    @Test
    public void testGetBookByID(){
        Book book=bookList.getBookByID("10001");
        assertEquals(book,bookList.get(0));
    }

    @Test
    public void testSetBook(){
        Book temp1=new Book("title1","author1","des1","img1","cate1","isbn1",5.0,66666,"azprice","azebprice","chprice","chebprice",0);
        bookList.addBook(temp1);
        Book temp2=new Book("title2","author2","des2","img2","cate2","isbn2",4.0,77777,"azprice","azebprice","chprice","chebprice",0);
        bookList.setBook(9,temp2);
        assertTrue(bookList.get(9).getTitle().equals("title2"));
        assertTrue(bookList.get(9).getAuthor().equals("author2"));
        assertTrue(bookList.get(9).getIsbn().equals("isbn2"));

        bookList.removeBook(9);
    }




    @Test
    public void find() {
        for(int i=0;i<bookList.size();i++){

            Book tbook=books[i];
            String tTitle=tbook.getTitle();
            String []titleElement=tTitle.split("\\s+");
            BookList result = bookList.find(tTitle);
            assertTrue(result.size()>0);
            result = bookList.find(tTitle.toUpperCase());
            assertTrue(result.size()>0);
            int bookFound=0;
            for(int j=0;j<result.size();j++){
                if (result.get(j)==tbook){
                    bookFound=1;
                }
            }
            assertTrue(bookFound==1);
            for(int k=0;k<titleElement.length;k++){
                assertTrue(bookList.find(titleElement[k]).size()>0);
            }
            //author
            String tAuthor=tbook.getAuthor();
            String[]authorElement=tAuthor.split("\\s+");
            assertTrue(bookList.find(tAuthor).size()>0);
            assertTrue(bookList.find(tAuthor.toUpperCase()).size()>0);
            for(int k=0;k<authorElement.length;k++){
                assertTrue(bookList.find(authorElement[k]).size()>0);
            }
            //isbn
            String tISBN=tbook.getIsbn();
            assertTrue(bookList.find(tISBN).size()>0);
            //category
            String tCategory=tbook.getCategory();
            assertTrue(bookList.find(tCategory).size()>0);
            assertTrue(bookList.find(tCategory.toUpperCase()).size()>0);

        }
        BookList result2=bookList.find("djaklfdjlkajeiojgklajfglkdjlkfjadkljfioajfiowjfoiajdlfjakljgioaj");
        assertTrue(result2.size()==0);
        result2=bookList.find(null);
        assertTrue(result2.size()==0);


    }

    @Test
    public void testSort(){
        for(int i=0;i<100;i++){
            book3.increaseViews();
        }
        bookList.sort();
        assertTrue(bookList.get(0).getAuthor().equals("Frances Hodgson Burnett"));
    }


    @Test
    public void  testEmptyList(){
        bookList.emptyList();
        assertTrue(bookList.size()==0);
    }


}