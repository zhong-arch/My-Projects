package com.example.AwemanyBooks.Presentation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AwemanyBooks.Presentation.BookInfoActivity;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.Presentation.ViewModels.TopBooksViewModel;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Presentation.RecyclerView.BookAdapter;

public class TopBooksFragment extends Fragment implements BookAdapter.OnBookListener {

    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private BookList topBooksBooks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topbooks, container, false);
        TopBooksViewModel topBooksViewModel = ViewModelProviders.of(this).get(TopBooksViewModel.class);

        initViews(view);
        initBookList(topBooksViewModel);
        setUpAdapter();
        return view;
    }


    public void initBookList(TopBooksViewModel topBooksViewModel){
        topBooksBooks = topBooksViewModel.getAllBooks();
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