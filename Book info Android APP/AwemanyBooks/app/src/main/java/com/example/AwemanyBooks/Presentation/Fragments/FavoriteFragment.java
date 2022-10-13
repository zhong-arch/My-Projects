package com.example.AwemanyBooks.Presentation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AwemanyBooks.Presentation.BookInfoActivity;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.R;
import com.example.AwemanyBooks.Presentation.RecyclerView.BookAdapter;
import com.example.AwemanyBooks.Presentation.ViewModels.FavoriteViewModel;

public class FavoriteFragment extends Fragment implements BookAdapter.OnBookListener{
    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;
    private BookList favoriteBooks;
    FavoriteViewModel favoriteViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        favoriteViewModel= ViewModelProviders.of(this).get(FavoriteViewModel.class);
        initViews(view);
        initBookList(favoriteViewModel);
        setUpAdapter();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        favoriteViewModel.update();
        initBookList(ViewModelProviders.of(this).get(FavoriteViewModel.class));
        setUpAdapter();
    }

    public void initBookList(AndroidViewModel favoriteViewModel) {
        favoriteBooks = ((FavoriteViewModel)favoriteViewModel).getAllBooks();
    }

    private void setUpAdapter() {
        bookAdapter = new BookAdapter(favoriteBooks, this);
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
        intent.putExtra("book_info",favoriteBooks.get(position));
        intent.putExtra("in_favourite",true);
        startActivity(intent);
    }
}
