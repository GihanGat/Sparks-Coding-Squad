package com.cambrian.bookshare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.cambrian.bookshare.model.Book;

import java.util.ArrayList;
import java.util.List;

import com.cambrian.bookshare.listeners.OnItemChangedListener;
import com.cambrian.bookshare.model.Books;

public class BooksAdapter extends RecyclerView.Adapter<BooksViewHolder> implements OnItemChangedListener {

    private ArrayList<Book> listOfBooks = new ArrayList<>();
    private Context context;

    public BooksAdapter(Context context, ArrayList<Book> listOfBooks) {
        this.context = context;
        this.listOfBooks = listOfBooks;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent , false);
        return new BooksViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        holder.populate(listOfBooks.get(position),this);

       // holder.getBook_title().setText(listOfBooks.get(position).getTitle());
       // holder.getBook_isbn().setText(listOfBooks.get(position).getIsbn());
    }

    @Override
    public int getItemCount() {
        return listOfBooks.size();
    }

    public Book getItem(int position) {
        return listOfBooks.get(position);
    }

    @Override
    public void onBookRemoved(Book book) {

    }

    @Override
    public void onDeleteClicked(View v, Book book) {

    }
}
