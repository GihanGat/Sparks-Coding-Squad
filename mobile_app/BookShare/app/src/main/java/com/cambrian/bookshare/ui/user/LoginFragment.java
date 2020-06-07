package com.cambrian.bookshare.ui.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.cambrian.bookshare.R;
import com.cambrian.bookshare.api.ServiceGenerator;
import com.cambrian.bookshare.model.AuthResponse;
import com.cambrian.bookshare.model.UserLogin;
import com.cambrian.bookshare.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private LoginViewModel loginViewModel;

    private Button button;
    private TextInputEditText emailText;
    private TextInputEditText passwordText;
    private NavController navController  = null;
    private Boolean loginSuccess  = false;


    @SuppressLint("WrongViewCast")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        emailText = (TextInputEditText)root.findViewById(R.id.editTextEmail);
        passwordText = (TextInputEditText)root.findViewById(R.id.editTextPassword);
        button = root.findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController  = Navigation.findNavController(view);
        view.findViewById(R.id.buttonLogin).setOnClickListener(this);
        view.findViewById(R.id.textViewRegister).setOnClickListener(this);
    }


    private void userLogin() {
        //String email = "gihanmad@gmail.com";
        //String password = "password";

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        //Call<AuthResponse> call = ServiceGenerator.getService().userLogin(email,password);
        UserLogin newUserLogin = new UserLogin(email, password);

        Call<AuthResponse> call = ServiceGenerator.getService().auth(newUserLogin);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                Toast.makeText(getContext(), authResponse.getToken(), Toast.LENGTH_LONG).show();
                loginSuccess = true;
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                if (loginSuccess) {
                    Navigation.findNavController(v).navigate(R.id.action_nav_login_to_nav_home);
                }else{
                    Toast.makeText(getContext(), "Please enter correct login details !", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.textViewRegister:
                    Navigation.findNavController(v).navigate(R.id.action_nav_login_to_nav_signup);
                break;
            default:
                break;
        }
    }
}
