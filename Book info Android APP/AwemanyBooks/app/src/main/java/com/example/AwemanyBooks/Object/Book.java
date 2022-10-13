package com.example.AwemanyBooks.Object;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.util.Comparator;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String description;
    private String imgUrl;
    private String category;
    private String isbn;
    private double score;
    private int drawableResource;
    private String azprice;
    private String azebprice;
    private String chprice;
    private String chebprice;
    private int views;

    //Empty constructor
    public Book(){

    }

    //constructor for testing
    public Book(int drawableResource){
        this.drawableResource = drawableResource;
    }

    //constructor main
    public Book(String title, String author, String description, String imgUrl, String category, String isbn,
                double score, int drawableResource, String azprice, String azebprice, String chprice, String chebprice, int views){
        this.title = title;
        this.author = author;
        this.description = description;
        this.imgUrl = imgUrl;
        this.category = category;
        this.isbn = isbn;
        this.score = score;
        this.drawableResource = drawableResource;
        this.azprice = azprice;
        this.azebprice = azebprice;
        this.chprice = chprice;
        this.chebprice = chebprice;
        this.views = views;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        description = in.readString();
        imgUrl = in.readString();
        category = in.readString();
        isbn = in.readString();
        score = in.readDouble();
        drawableResource = in.readInt();
        azprice = in.readString();
        azebprice = in.readString();
        chprice = in.readString();
        chebprice = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getIsbn() { return isbn; }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public String getAzprice() { return azprice;}

    public String getAzebprice() { return azebprice; }

    public String getChprice() { return chprice;}

    public String getChebprice() { return chebprice;}

    public int getViews() { return views;}

    public void increaseViews() { this.views++;}

    public void setDrawableResource(int drawableResource) {
        this.drawableResource = drawableResource;
    }
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public static Comparator<Book> BookViews = new Comparator<Book>() {

        public int compare(Book s1, Book s2) {

            int rollno1 = s1.getViews();
            int rollno2 = s2.getViews();

            return rollno2-rollno1;
        }};

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(description);
        dest.writeString(imgUrl);
        dest.writeString(category);
        dest.writeString(isbn);
        dest.writeDouble(score);
        dest.writeInt(drawableResource);
        dest.writeString(azprice);
        dest.writeString(azebprice);
        dest.writeString(chprice);
        dest.writeString(chebprice);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof  Book){
            Book t=(Book)obj;
            return this.isbn.equals(t.isbn);
        }
        return false;
    }
}
