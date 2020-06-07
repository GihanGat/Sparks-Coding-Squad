package com.cambrian.bookshare.ui.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cambrian.bookshare.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private Button signupButton;
    private TextInputEditText emailText;
    private TextInputEditText passwordText;
    private TextInputEditText nameText;
    private NavController navController  = null;
    private Boolean signupSuccess = false;
    private View root;


    private Button buttonSave;
    private Button buttonChangePassword;
    private Button buttonLogout;
    private Button buttonDelete;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextName;
    private TextInputEditText editContactNumber;
    private TextInputEditText editTextCurrentPassword;
    private TextInputEditText editTextNewPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_upadate_user, container, false);
        editTextEmail = (TextInputEditText)root.findViewById(R.id.editTextEmail);
        editTextName = (TextInputEditText)root.findViewById(R.id.editTextName);
        editContactNumber = (TextInputEditText)root.findViewById(R.id.editContactNumber);
        buttonSave = root.findViewById(R.id.buttonSave);

        editTextCurrentPassword = (TextInputEditText)root.findViewById(R.id.editTextCurrentPassword);
        editTextNewPassword = (TextInputEditText)root.findViewById(R.id.editTextNewPassword);
        buttonChangePassword = root.findViewById(R.id.buttonChangePassword);

        buttonLogout = root.findViewById(R.id.buttonLogout);
        buttonDelete = root.findViewById(R.id.buttonDelete);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return root;
    }
}
