package com.cambrian.bookshare.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cambrian.bookshare.R;
import com.cambrian.bookshare.api.ServiceGenerator;
import com.cambrian.bookshare.listeners.OnNewItemListener;
import com.cambrian.bookshare.model.Book;
import com.cambrian.bookshare.model.BookResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewBookDialogFragment extends DialogFragment implements View.OnClickListener {



    public static class Builder{
        private AddNewBookDialogFragment dFragment = new AddNewBookDialogFragment();

        public Builder addOnNewItemListener(OnNewItemListener listener){
            dFragment.listener = listener;
            return this;
        }

        public void show(FragmentManager fragmentManager, String tag){
            dFragment.show(fragmentManager,tag);
        }
    }

    private OnNewItemListener listener;

    private TextInputEditText titleEditText;
    private TextInputEditText authorEditText;
    private TextInputEditText isbnEditText;
    private Spinner categorySpinner;
    private Spinner yearSpinner;
    private TextInputEditText countryEditText;
    private TextInputEditText notesEditText;
    private Button addBookButton;
    private TextInputEditText priceEditText;


    private String bookTitle;
    private String bookCategory;
    private String bookAuthor;
    private Long bookisbn;
    private Integer year;
    private String country;
    private String notes;
    private Integer price;

    private NavController navController  = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        View layout = inflater.inflate(R.layout.fragment_add_new_book, container, false);


        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2000; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, years);

        yearSpinner = layout.findViewById(R.id.new_book_published_year_spinner);
        yearSpinner.setAdapter(adapter);


        titleEditText = layout.findViewById(R.id.new_book_title);
        authorEditText = layout.findViewById(R.id.new_book_author);
        isbnEditText = layout.findViewById(R.id.new_book_isbn);
        categorySpinner = layout.findViewById(R.id.new_book_category_spinner);
        //countryEditText = layout.findViewById(R.id.new_book_country);
        notesEditText = layout.findViewById(R.id.new_book_notes);
        priceEditText = layout.findViewById(R.id.new_book_price);

        //navController  = Navigation.findNavController(view);
        addBookButton = layout.findViewById(R.id.buttonAddNewBook);
        //addBookButton.setOnClickListener(this::addButtonClick);

        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController  = Navigation.findNavController(view);
        addBookButton = view.findViewById(R.id.buttonAddNewBook);
        addBookButton.setOnClickListener(this);
    }


    public void addButtonClick(View view){
        List<String> bookAuthors = null;
        List<String> bookCategories = null;

        if (titleEditText.getText() == null || titleEditText.getText().toString().isEmpty()){
            return;
        }else{
            bookTitle = titleEditText.getText().toString();
        }

        if (!String.valueOf(categorySpinner.getSelectedItem().toString()).isEmpty()) {
            bookCategory = String.valueOf(categorySpinner.getSelectedItem().toString());
            bookCategories = Arrays.asList(bookCategory);
        }else{
            bookCategory = "";
        }

        if (!authorEditText.getText().toString().isEmpty()) {
            bookAuthor = authorEditText.getText().toString();
            bookAuthors = Arrays.asList(bookAuthor.split(",", -1));
        }else{
            bookAuthor = "";
        }

        if (!isbnEditText.getText().toString().isEmpty()) {
            bookisbn = Long.parseLong(isbnEditText.getText().toString());
        }else{
            bookisbn = Long.valueOf(0);
        }

        if (!yearSpinner.getSelectedItem().toString().isEmpty()) {
            year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
        }

        if (!priceEditText.getText().toString().isEmpty()) {
            price = Integer.parseInt(priceEditText.getText().toString());
        }else{
            price = Integer.valueOf(0);
        }

//        if (!countryEditText.getText().toString().isEmpty()) {
//            country = countryEditText.getText().toString();
//        }else{
//            country = "";
//        }

        if(!notesEditText.getText().toString().isEmpty()){
            notes = notesEditText.getText().toString();
        }else{
            notes = "";
        }

        if (bookTitle.isEmpty()){
            return;
        }

       // BookList.getInstance().addBook(bookTitle,bookCategory,bookAuthor,bookisbn,year, country,notes);
        Book newBook = new Book(bookAuthors, bookisbn.toString(), bookTitle, notes, year, bookCategories , price);

        Call<BookResponse> call = ServiceGenerator.getService().getResponse(newBook);

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                //Log.d("Success", "onSuccess: " + response.body().getMessage());
                Navigation.findNavController(view).navigate(R.id.action_addNewBookDialogFragment_to_nav_home);


              //  Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.d("FAILED", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(),"Failed " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        if (listener != null){
            listener.onNewItem();
        }
        dismiss();
    }

    @Override
    public void onClick(View v) {
        List<String> bookAuthors = null;
        List<String> bookCategories = null;

        if (titleEditText.getText() == null || titleEditText.getText().toString().isEmpty()){
            return;
        }else{
            bookTitle = titleEditText.getText().toString();
        }

        if (!String.valueOf(categorySpinner.getSelectedItem().toString()).isEmpty()) {
            bookCategory = String.valueOf(categorySpinner.getSelectedItem().toString());
            bookCategories = Arrays.asList(bookCategory);
        }else{
            bookCategory = "";
        }

        if (!authorEditText.getText().toString().isEmpty()) {
            bookAuthor = authorEditText.getText().toString();
            bookAuthors = Arrays.asList(bookAuthor.split(",", -1));
        }else{
            bookAuthor = "";
        }

        if (!isbnEditText.getText().toString().isEmpty()) {
            bookisbn = Long.parseLong(isbnEditText.getText().toString());
        }else{
            bookisbn = Long.valueOf(0);
        }

        if (!yearSpinner.getSelectedItem().toString().isEmpty()) {
            year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
        }

        if (!priceEditText.getText().toString().isEmpty()) {
            price = Integer.parseInt(priceEditText.getText().toString());
        }else{
            price = Integer.valueOf(0);
        }

//        if (!countryEditText.getText().toString().isEmpty()) {
//            country = countryEditText.getText().toString();
//        }else{
//            country = "";
//        }

        if(!notesEditText.getText().toString().isEmpty()){
            notes = notesEditText.getText().toString();
        }else{
            notes = "";
        }

        if (bookTitle.isEmpty()){
            return;
        }

        // BookList.getInstance().addBook(bookTitle,bookCategory,bookAuthor,bookisbn,year, country,notes);
        Book newBook = new Book(bookAuthors, bookisbn.toString(), bookTitle, notes, year, bookCategories , price);

        Call<BookResponse> call = ServiceGenerator.getService().getResponse(newBook);

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                //Log.d("Success", "onSuccess: " + response.body().getMessage());
                Navigation.findNavController(v).navigate(R.id.action_addNewBookDialogFragment_to_nav_home);


                //  Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.d("FAILED", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(),"Failed " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}