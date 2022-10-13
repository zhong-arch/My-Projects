package com.example.AwemanyBooks.Presentation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AwemanyBooks.Presentation.BookInfoActivity;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Presentation.RecyclerView.BookAdapter;
import com.example.AwemanyBooks.Presentation.ViewModels.HomeViewModel;

public class HomeFragment extends Fragment implements BookAdapter.OnBookListener {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private BookList bookList;
    private static BookList originalBookList;
    private String searchInput;
    private EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        initViews(view);
        initBookList(homeViewModel);
        setUpAdapter(originalBookList);

        final ImageButton searchButton;

        searchButton = view.findViewById(R.id.search_button);
        editText = view.findViewById(R.id.search_input);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchInput = editText.getText().toString().toLowerCase().trim();
                if(searchInput.length() > 0){
                    bookList = originalBookList.find(searchInput);
                    setUpAdapter(bookList);
                }
                else{
                    bookList = originalBookList;
                    setUpAdapter(bookList);
                }
            }
        });

        initBookList(homeViewModel);
        setUpAdapter(originalBookList);
        return view;
    }

    private void setUpAdapter(BookList newBooklist) {
        bookAdapter = new BookAdapter(newBooklist, this);
        recyclerViewBooks.setAdapter(bookAdapter);
    }

    private void initBookList(HomeViewModel homeViewModel) {
        bookList = homeViewModel.getAllBooks();
        originalBookList = homeViewModel.getAllBooks();
    }
    private void initViews(View view) {
        recyclerViewBooks = (RecyclerView)view.findViewById(R.id.main_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBooks.setLayoutManager(layoutManager);
        recyclerViewBooks.setHasFixedSize(true);
    }

    @Override
    public void onBookClick(int position) {
        Log.d("MainActivity", "onBookClick: clicked.");
        Intent intent = new Intent(getContext(), BookInfoActivity.class);
        intent.putExtra("book_info",bookList.get(position));
        startActivity(intent);
    }

    public boolean resetUpAdapter(BookList newBooklist) {
        bookAdapter = new BookAdapter(newBooklist, this);
        recyclerViewBooks.setAdapter(bookAdapter);
        if(bookAdapter!=null){
            return true;
        }
        return false;
    }
}