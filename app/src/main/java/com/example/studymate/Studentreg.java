package com.example.studymate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Studentreg extends Fragment {
    EditText etFullName, etEmail, etPassword;
    FloatingActionButton btnSignUp;
    DBHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studentreg, container, false);

        etFullName = view.findViewById(R.id.RSname);
        etEmail = view.findViewById(R.id.RSmail);
        etPassword = view.findViewById(R.id.RSpass);
        btnSignUp = view.findViewById(R.id.RS);

        databaseHelper = new DBHelper(getActivity());

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserEmail = databaseHelper.checkEmail(email);

                    if (checkUserEmail) {
                        Toast.makeText(getActivity(), "User already exists! Please login", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean insert = databaseHelper.insertData(fullName, email, password);

                        if (insert) {
                            Toast.makeText(getActivity(), "Signup Successful!", Toast.LENGTH_SHORT).show();
                            //Intent intent = new Intent(getActivity(), Studentlogin.class);
                            //startActivity(intent);


                        } else {
                            Toast.makeText(getActivity(), "Signup Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return view;
    }
}
