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
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Presentation.RecyclerView.BookAdapter;
import com.example.AwemanyBooks.Presentation.ViewModels.FictionViewModel;


public class FictionFragment extends Fragment implements BookAdapter.OnBookListener {

    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private BookList fictionBooks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fiction, container, false);
        FictionViewModel fictionViewModel = ViewModelProviders.of(this).get(FictionViewModel.class);

        initViews(view);
        initBookList(fictionViewModel);
        setUpAdapter();
        return view;
    }


    public void initBookList(FictionViewModel fictionViewModel) {
        fictionBooks = fictionViewModel.getAllBooks();
    }


    private void setUpAdapter() {
        bookAdapter = new BookAdapter(fictionBooks, this);
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
        intent.putExtra("book_info",fictionBooks.get(position));
        startActivity(intent);
    }
}