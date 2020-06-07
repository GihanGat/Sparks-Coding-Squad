package com.cambrian.bookshare.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cambrian.bookshare.BooksAdapter;
import com.cambrian.bookshare.R;
import com.cambrian.bookshare.api.ServiceGenerator;
import com.cambrian.bookshare.model.Book;
import com.cambrian.bookshare.model.Books;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private BooksAdapter booksAdapter;
    private RecyclerView books_recyclerview;
    private NavController navController  = null;
    private FloatingActionButton flb;

    private ArrayList<Book> listOfBooks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // container.removeAllViews();
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        Toolbar toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        books_recyclerview = (RecyclerView) root.findViewById(R.id.books_recyclerview);
        // Set the layout manager
        books_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }



    private void onAddBookClick(View v) {
        // Toast.makeText(getContext(),"CLicked FLB",Toast.LENGTH_SHORT).show();
        //new AddNewBookDialogFragment.Builder()
        //        .addOnNewItemListener(booksAdapter::notifyDataSetChanged)
        //        .show(((AppCompatActivity)getActivity()).getSupportFragmentManager(),"add_new_book");

        Navigation.findNavController(v).navigate(R.id.action_nav_home_to_addNewBookDialogFragment);
        //booksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController  = Navigation.findNavController(view);
        flb = view.findViewById(R.id.add_new_book);
        flb.setOnClickListener(this::onAddBookClick);
        //view.findViewById(R.id.add_new_book).setOnClickListener(this::onAddBookClick);
    }

}
