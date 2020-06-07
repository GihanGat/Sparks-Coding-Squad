package com.cambrian.bookshare.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Books implements Serializable {

    @SerializedName("bookList")
    private  ArrayList<Book> bookList;

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public ArrayList<Book> getBookByID(String id) {
        return bookList;
    }

    public Book getBook(int position){
        return bookList.get(position);
    }

//    public Book getBookFromID(String id){
//        int count = 0;
//        while (bookList.size() > count) {
//            if (bookList.get(count).getId() == id ) {
//                returnBook = bookList.get(count);
//                break;
//            }
//            count++;
//        }
//        return returnBook;
//    }

}