package com.cambrian.bookshare;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cambrian.bookshare.listeners.OnItemChangedListener;
import com.cambrian.bookshare.model.Book;

public class BooksViewHolder extends RecyclerView.ViewHolder  {

    private TextView book_title , book_isbn;
    private View wishListButtonContainer;

    private Book book;
    private OnItemChangedListener listener;

    public BooksViewHolder(@NonNull View itemView) {
        super(itemView);

        book_title = (TextView)itemView.findViewById(R.id.book_title);
        book_isbn  = (TextView)itemView.findViewById(R.id.book_isbn);
        wishListButtonContainer = (View)itemView.findViewById(R.id.wishlist_button_container);
    }

    public  TextView getBook_title(){
        return book_title;
    }

    public  TextView getBook_isbn(){
        return book_isbn;
    }


    public void populate(Book book, OnItemChangedListener listener) {
        this.book = book;
        this.listener = listener;

        //book_title.setText(book.getTitle());
        //book_isbn.setText(book.getIsbn());

        this.getBook_title().setText(book.getTitle());
        this.getBook_isbn().setText(book.getIsbn());

        itemView.setOnClickListener(this::onItemClick);

    }

    private void onItemClick(View v) {
    }


}
