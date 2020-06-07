package com.cambrian.bookshare.api;

import com.cambrian.bookshare.model.AuthResponse;
import com.cambrian.bookshare.model.Book;
import com.cambrian.bookshare.model.BookResponse;
import com.cambrian.bookshare.model.Books;
import com.cambrian.bookshare.model.SignupResponse;
import com.cambrian.bookshare.model.User;
import com.cambrian.bookshare.model.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth")
    Call<AuthResponse> auth(@Body UserLogin newUserLogin);


//    @GET("books/{id}")
//    Call<Books> getBook(@Path("id") String id);

    @POST("books/new")
    Call<BookResponse> getResponse(@Body Book newBook);

    @POST("users")
    Call<SignupResponse> newUserSignup(@Body User newUser);


}
