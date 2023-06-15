package com.example.studymate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Adminlogin extends AppCompatActivity {

    private EditText adminIdEditText;
    private EditText passwordEditText;
    private FloatingActionButton loginButton;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        dbHelper = new DBHelper(this);

        adminIdEditText = findViewById(R.id.Amail);
        passwordEditText = findViewById(R.id.Apass);
        loginButton = findViewById(R.id.Alogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adminId = adminIdEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean adminInserted = dbHelper.insertAdminData("admin123","password123");

                if (adminInserted) {
                    Toast.makeText(Adminlogin.this, "admin registered", Toast.LENGTH_SHORT).show();
                }



                    // Admin data inserted successfully
                    boolean loginSuccessful = dbHelper.checkAdminLoginCredentials(adminId, password);

                    if (loginSuccessful) {
                        // Admin login successful, navigate to admin dashboard
                        Toast.makeText(Adminlogin.this, "Admin login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Adminlogin.this, Admindashboard.class);
                        startActivity(intent);
                        finish(); // Optional: finish the login activity
                    } else {
                        // Admin login failed, show error message or take appropriate action
                        Toast.makeText(Adminlogin.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                    }
                }
        });

    }
}
