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
import com.example.AwemanyBooks.Presentation.ViewModels.LiteraryViewModel;

public class LiteraryFragment extends Fragment implements BookAdapter.OnBookListener {

    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private BookList literaryBooks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_literary, container, false);
        LiteraryViewModel literaryViewModel = ViewModelProviders.of(this).get(LiteraryViewModel.class);

        initViews(view);
        initBookList(literaryViewModel);
        setUpAdapter();
        return view;
    }


    public void initBookList(LiteraryViewModel literaryViewModel){
       literaryBooks = literaryViewModel.getAllBooks();
    }


    private void setUpAdapter() {
        bookAdapter = new BookAdapter(literaryBooks, this);
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
        intent.putExtra("book_info",literaryBooks.get(position));
        startActivity(intent);
    }
}