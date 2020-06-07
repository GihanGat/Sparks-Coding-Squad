package com.cambrian.bookshare.listeners;

import android.view.View;

import com.cambrian.bookshare.model.Book;

public interface OnItemChangedListener {
    void onBookRemoved(Book book);
    void onDeleteClicked(View v, Book book);
}

