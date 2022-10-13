package com.example.AwemanyBooks.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AwemanyBooks.Business.AccessComment;
import androidx.annotation.Nullable;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Business.AccessComment;
import com.example.AwemanyBooks.Object.Comment;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.application.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RatingActivity extends AppCompatActivity {

    private AccessComment accessComment;
    private RatingBar rating;
    private TextView text;
    private EditText review;
    private Button submit;
    private String userID;
    private String bid;
    private String comments;
    private double ratingScore;
    private java.sql.Date date;
    private Comment comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Book book = intent.getParcelableExtra("book");
        bid = book.getIsbn();
        setContentView(R.layout.rating_review);
        rating = findViewById(R.id.Review_rating);
        text  = findViewById(R.id.Review_text1);
        review=findViewById(R.id.Review_edit);
        submit=findViewById(R.id.Review_submit);
        userID = Service.getCurrentUser();
        accessComment = new AccessComment(userID);
        comment = accessComment.getComment( userID, bid );

        if( comment != null ){
            review.setText( comment.getComment() );
            rating.setRating( (float)comment.getRating() );
        }
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ratingScore = (double)rating.getRating();

                comments=review.getText().toString();
                date=new java.sql.Date(Calendar.getInstance().getTime().getTime());
                accessComment.insertComment(userID, bid, comments, date, ratingScore);
                Toast.makeText(RatingActivity.this, "Thank you for rating", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent =new Intent(RatingActivity.this,MainActivity.class);
                startActivity(intent);
                
            }
        });
    }
}
