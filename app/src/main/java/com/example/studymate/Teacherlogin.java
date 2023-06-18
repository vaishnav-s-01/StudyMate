package com.example.studymate;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Teacherlogin extends Fragment {

    private EditText nameEditText;
    private EditText passwordEditText,id;
    private FloatingActionButton loginButton;

    private DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacherlogin, container, false);

        nameEditText = view.findViewById(R.id.Tname);
        passwordEditText = view.findViewById(R.id.Tpass);
        loginButton = view.findViewById(R.id.Tlogin);
        id = view.findViewById(R.id.Tid);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String Tid = id.getText().toString();

                Log.d(TAG, "onClick: hi"+name);
                Log.d(TAG, "onClick: hi"+password);
                Log.d(TAG, "onClick: hi"+Tid);
                boolean loginSuccessful = dbHelper.checkTeacherLoginCredentials(Tid, password,name,"1");

                if (loginSuccessful) {
                    // Teacher login successful, navigate to teacher's dashboard or desired screen
                    Toast.makeText(getContext(), "Teacher login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), TeacherDashboard.class);
                    startActivity(intent);
                    getActivity().finish();
                    // Perform necessary actions for teacher login
                } else {
                    // Teacher login failed, show error message or take appropriate action
                    Toast.makeText(getContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
