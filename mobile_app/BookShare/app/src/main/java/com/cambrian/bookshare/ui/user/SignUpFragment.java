package com.cambrian.bookshare.ui.user;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cambrian.bookshare.R;

import com.cambrian.bookshare.api.ServiceGenerator;
import com.cambrian.bookshare.model.AuthResponse;
import com.cambrian.bookshare.model.SignupResponse;
import com.cambrian.bookshare.model.User;
import com.cambrian.bookshare.model.UserLogin;
import com.cambrian.bookshare.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends Fragment implements View.OnClickListener{

    private Button signupButton;
    private TextInputEditText emailText;
    private TextInputEditText passwordText;
    private TextInputEditText nameText;
    private TextInputEditText contactNoText;

    private NavController navController  = null;
    private Boolean signupSuccess = false;
    private View root;

    @SuppressLint("WrongViewCast")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_signup, container, false);

        emailText = (TextInputEditText)root.findViewById(R.id.editTextEmail);
        passwordText = (TextInputEditText)root.findViewById(R.id.editTextPassword);
        nameText = (TextInputEditText)root.findViewById(R.id.editTextName);
        contactNoText = (TextInputEditText)root.findViewById(R.id.editTextContactNumber);
        signupButton = root.findViewById(R.id.buttonSignUp);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController  = Navigation.findNavController(view);
        view.findViewById(R.id.buttonSignUp).setOnClickListener(this);
        view.findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void userSignup() {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String name = nameText.getText().toString();
        //Call<AuthResponse> call = ServiceGenerator.getService().userLogin(email,password);
        User newUser = new User(name,email, password);

        Call<SignupResponse> call = ServiceGenerator.getService().newUserSignup(newUser);

        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse = response.body();
                Toast.makeText(getContext(), "New user accoutn has been created successfully", Toast.LENGTH_LONG).show();
                signupSuccess = true;
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                if (signupSuccess) {
                    Navigation.findNavController(v).navigate(R.id.action_nav_signup_to_nav_login);
                }else{
                    Toast.makeText(getContext(), "Somethign wrong with new user details , check entered details", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.textViewLogin:
                Navigation.findNavController(v).navigate(R.id.action_nav_signup_to_nav_login);
                break;
            default:
                break;
        }
    }
}
