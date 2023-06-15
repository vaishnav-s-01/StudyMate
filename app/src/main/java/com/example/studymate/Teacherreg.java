package com.example.studymate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Teacherreg extends Fragment {

    private EditText teacherIdEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText fullNameEditText;

    private DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacherreg, container, false);

        teacherIdEditText = view.findViewById(R.id.RTid);
        emailEditText = view.findViewById(R.id.RTmail);
        passwordEditText = view.findViewById(R.id.RTpass);
        fullNameEditText = view.findViewById(R.id.RTname);
        FloatingActionButton registerButton = view.findViewById(R.id.RT);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teacherId = teacherIdEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String fullName = fullNameEditText.getText().toString();

                boolean inserted = dbHelper.insertTeacherData(teacherId, email, password, fullName);

                if (inserted) {
                    Toast.makeText(getContext(), "Teacher registered successfully", Toast.LENGTH_SHORT).show();
                    // Clear input fields or navigate to a new screen
                } else {
                    Toast.makeText(getContext(), "Teacher registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
