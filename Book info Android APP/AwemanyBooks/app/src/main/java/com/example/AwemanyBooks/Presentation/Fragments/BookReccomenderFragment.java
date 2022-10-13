package com.example.AwemanyBooks.Presentation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AwemanyBooks.Presentation.BookInfoActivity;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.Presentation.ViewModels.BookReccomendationViewModel;
import com.example.AwemanyBooks.Presentation.ViewModels.FavoriteViewModel;
import com.example.AwemanyBooks.Presentation.ViewModels.TopBooksViewModel;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Presentation.RecyclerView.BookAdapter;

public class BookReccomenderFragment extends Fragment implements BookAdapter.OnBookListener {

    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private BookList topBooksBooks;
    BookReccomendationViewModel bookReccomendationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookreccomender, container, false);
        bookReccomendationViewModel = ViewModelProviders.of(this).get(BookReccomendationViewModel.class);

        initViews(view);
        initBookList(bookReccomendationViewModel);
        initListeners(view);
        setUpAdapter();
        return view;
    }



    private void initListeners(final View view){
        Button updateButton = (Button) view.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookReccomendationViewModel.update();
                initBookList(bookReccomendationViewModel);
                setUpAdapter();
            }
        });
    }
    public void initBookList(BookReccomendationViewModel bookReccomendationViewModel){
        topBooksBooks = bookReccomendationViewModel.getAllBooks();
    }


    private void setUpAdapter() {
        bookAdapter = new BookAdapter(topBooksBooks, this);
        recyclerViewBooks.setAdapter(bookAdapter);
    }



    private void initViews(View view) {
        recyclerViewBooks = (RecyclerView) view.findViewById(R.id.main_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBooks.setLayoutManager(layoutManager);
        recyclerViewBooks.setHasFixedSize(true);

    }
    @Override
    public void onBookClick(int position) {
        Log.d("MainActivity", "onBookClick: clicked.");
        Intent intent = new Intent(getContext(), BookInfoActivity.class);
        intent.putExtra("book_info",topBooksBooks.get(position));
        startActivity(intent);
    }
}
