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

public class Studentlogin extends Fragment {
    EditText etFullName, etPassword;
    FloatingActionButton btnLogin;
    DBHelper databaseHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_studentlogin, container, false);

        etFullName = view.findViewById(R.id.Sname);
        etPassword = view.findViewById(R.id.Spass);
        btnLogin = view.findViewById(R.id.Slogin);

        databaseHelper = new DBHelper(getActivity());


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFullName.getText().toString();
                String pass = etPassword.getText().toString();

                // Verify if any field is empty
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isValidUser = databaseHelper.checkLoginCredentials(name, pass);
                if (isValidUser) {
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    // Navigate to the StudentDashboard page or perform the desired action
                    // For example, you can use Intent to start a new activity
                    Intent intent = new Intent(getActivity(), StudentDashboard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
