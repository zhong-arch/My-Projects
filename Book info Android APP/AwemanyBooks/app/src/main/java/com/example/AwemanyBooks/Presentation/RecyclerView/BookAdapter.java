package com.example.AwemanyBooks.Presentation.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.AwemanyBooks.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AwemanyBooks.Business.BookList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{


    BookList bookList;
    private OnBookListener mOnBookListener;
    public BookAdapter(BookList bookList, OnBookListener onBookListener){
        this.bookList = bookList;
        this.mOnBookListener=onBookListener;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false );

        return new BookViewHolder(view,mOnBookListener);
    }

    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        // bind book's info
        // This binds only image of the book

        //load book image using Glide
        Glide.with(holder.itemView.getContext())
                .load(bookList.get(position).getDrawableResource())
                .transforms(new CenterCrop(), new RoundedCorners(16))
                .into(holder.imgBook);
        System.out.println("Glide is "+bookList.get(position).getDrawableResource());

        TextView authorTextView = (TextView)holder.itemView.findViewById(R.id.item_book_author_txt);
        authorTextView.setText(bookList.get(position).getAuthor());
        TextView titleTextView = (TextView)holder.itemView.findViewById(R.id.item_book_title_txt);
        titleTextView.setText(bookList.get(position).getTitle());
        TextView categoryTextView = (TextView)holder.itemView.findViewById((R.id.item_book_category_txt));
        categoryTextView.setText(bookList.get(position).getCategory());
        TextView isbnTextView = (TextView)holder.itemView.findViewById((R.id.item_book_isbn_txt));
        isbnTextView.setText("ISBN: "+ bookList.get(position).getIsbn());
        holder.author = authorTextView;
        holder.author = titleTextView;
        holder.ratingBar.setRating((float)bookList.get(position).getScore());

    }

    public int getItemCount(){
        return bookList.size();
    }


    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgBook;
        private ImageView imgFav;
        private TextView title;
        private TextView author;
        private TextView category;
        private TextView isbn;
        private RatingBar ratingBar;
        OnBookListener onBookListener;


        public BookViewHolder(@NonNull View itemView, OnBookListener onBookListener){
            super(itemView);


            imgBook = itemView.findViewById(R.id.item_book_img);
            title = itemView.findViewById(R.id.item_book_title_txt);
            author = itemView.findViewById(R.id.item_book_author_txt);
            category = itemView.findViewById(R.id.item_book_category_txt);
            isbn = itemView.findViewById(R.id.item_book_isbn_txt);
            ratingBar = itemView.findViewById(R.id.item_book_ratingBar);

            this.onBookListener=onBookListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onBookListener.onBookClick(getAdapterPosition());
        }

    }
    public interface OnBookListener{
        void onBookClick(int position);

    }
}
