package com.example.AwemanyBooks.Presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AwemanyBooks.Business.AccessBooks;
import com.example.AwemanyBooks.Business.AccessComment;
import com.example.AwemanyBooks.Business.AccessFavorite;
import com.example.AwemanyBooks.Business.AccessUserHistory;
import com.example.AwemanyBooks.Business.CommentList;
import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Object.Comment;
import com.example.AwemanyBooks.Presentation.RecyclerView.CommentAdapter;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.application.Service;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {
    Button favorite;
    Button removeFavorite;
    Button writeReview;
    private EditText commentText;
    private AccessBooks accessBooks;
    private AccessComment accessComment;
//    private ArrayList<Comment> comments;
    private CommentList comments;
    private RecyclerView recyclerViewComments;
    private CommentAdapter commentAdapter;
    private String bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        Intent intent = getIntent();

        Book book = intent.getParcelableExtra("book_info");

        Boolean inFavourite = intent.getBooleanExtra("in_favourite",false);
        String title = book.getTitle();
        String author = book.getAuthor();
        String description = book.getDescription();
        String rating = ""+book.getScore();
        final String isbn = book.getIsbn();

        bid = isbn;
        String azprice = book.getAzprice();
        String azebprice = book.getAzebprice();
        String chprice = book.getChprice();
        String chebprice = book.getChebprice();
        final String userID = Service.getCurrentUser();
        TextView titleView = findViewById(R.id.book_title);
        titleView.setText(title);

        accessBooks = new AccessBooks();
        book.increaseViews();
        accessBooks.updateBook(book);

        favorite =  findViewById(R.id.fav);
        removeFavorite = findViewById(R.id.remove_fav);
        writeReview = findViewById(R.id.review);

        TextView authorView = findViewById(R.id.author);
        authorView.setText(author);

        TextView desView = findViewById(R.id.des_view);
        desView.setText(description);
        desView.setMovementMethod(new ScrollingMovementMethod());

        TextView ratingView = findViewById(R.id.rating_num);
        ratingView.setText(rating);

        TextView isbnView = findViewById(R.id.ISBN);
        isbnView.setText(isbn);

        RatingBar ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setRating((float)book.getScore());

        int imgID=book.getDrawableResource();
        ImageView bookImg=findViewById(R.id.bookimg);
        bookImg.setImageResource(imgID);

        TextView azpriceView = findViewById(R.id.az_price);
        azpriceView.setText("Paper: "+azprice);

        TextView azebpriceView = findViewById(R.id.azeb_price);
        azebpriceView.setText("E-book: "+azebprice);

        TextView chpriceView = findViewById(R.id.ch_price);
        chpriceView.setText("Paper: "+chprice);

        TextView chebpriceView = findViewById(R.id.cheb_price);
        chebpriceView.setText("E-book: "+chebprice);




        updateHistoryRecord( userID, isbn );//update history

        setFavoriteUI(inFavourite, userID, isbn);

        initComments( accessComment );
        recyclerViewComments = findViewById(R.id.comment_recyclerView);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setHasFixedSize(true);
        commentAdapter = new CommentAdapter( comments );
        recyclerViewComments.setAdapter(commentAdapter);

        review(book);

        //set spinner
        final String[]sortType=new String []{" date↑","date↓", "rating↑","rating↓"};
        ArrayAdapter<String> sortAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sortType);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner= (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(sortAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result=parent.getItemAtPosition(position).toString();
//                Toast.makeText(BookInfoActivity.this,result, Toast.LENGTH_SHORT).show();
                if(result.equals(sortType[0])){
//                    commentAdapter.sortByDate();
                    comments.sortByDate();
                }
                else if(result.equals(sortType[1])){
//                    commentAdapter.sortByDateDes();
                    comments.sortByDateDes();
                }
                else if(result.equals(sortType[2])){
//                    commentAdapter.sortByRating();
                    comments.sortByRating();
                }
                else{
//                    commentAdapter.sortByRatingDes();
                    comments.sortByRatingDes();
                }
                commentAdapter=new CommentAdapter(comments);
                recyclerViewComments.setAdapter(commentAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setFavoriteUI(boolean inFavourite, final String userID,final String isbn){
        if(Service.getCurrentUser() == "local"){
            favorite.setVisibility(View.INVISIBLE);
            removeFavorite.setVisibility(View.INVISIBLE);
        }else {
            if (inFavourite) {
                favorite.setVisibility(View.INVISIBLE);
                removeFavorite.setVisibility(View.VISIBLE);
                removeFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeFavorite(userID, isbn);
                    }
                });
            } else {
                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateFavorite(userID, isbn);
                    }
                });
            }
        }
    }

    private void review(final Book book){
        if(Service.getCurrentUser() == "local"){
            writeReview.setVisibility(View.INVISIBLE);

        }
        else{
            writeReview.setVisibility(View.VISIBLE);
            writeReview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent2 =new Intent(BookInfoActivity.this,RatingActivity.class);
                    intent2.putExtra("book",book);
                    startActivity(intent2);
                }
            });

        }
    }

    public void updateHistoryRecord( String uid, String bid ){
        AccessUserHistory accessUserHistory = new AccessUserHistory( uid );
        BookList books = accessUserHistory.getBooks();
        if( !books.searchBookByID( bid ) ){
            accessUserHistory.insertBook(uid,bid);
        }
    }
    public void updateFavorite( String uid, String bid ){
        AccessFavorite accessFavorite = new AccessFavorite( uid );
        BookList books = accessFavorite.getBooks();
        if( !books.searchBookByID( bid ) ){
            Toast.makeText(getApplicationContext(), "Book Successfully Added To Favorites", Toast.LENGTH_LONG).show();
            accessFavorite.insertBook(uid,bid);
        }else {
            Toast.makeText(getApplicationContext(), "Book Already In Favourites", Toast.LENGTH_LONG).show();
        }
    }
    public void removeFavorite( String uid, String bid ){
        AccessFavorite accessFavorite = new AccessFavorite( uid );
        BookList books = accessFavorite.getBooks();
        if( books.searchBookByID( bid ) ){
            Toast.makeText(getApplicationContext(), "Book Successfully Removed From Favorites", Toast.LENGTH_LONG).show();
            accessFavorite.deleteBook(uid,bid);
            getSupportFragmentManager().popBackStack();
        }else {
            Toast.makeText(getApplicationContext(), "Error Removing Book From Favourites", Toast.LENGTH_LONG).show();
        }
    }
    private void initComments(AccessComment accessComment) {
        accessComment = new AccessComment( bid );
        comments = accessComment.getComments();
    }
}
