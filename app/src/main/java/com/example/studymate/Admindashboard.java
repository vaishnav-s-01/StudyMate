package com.example.studymate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Admindashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name,email,id;
    DBHelper DB;
    MyAdapter adapter;
    Button approve,delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        id = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,name,email,id);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
        approve = findViewById(R.id.approve);
        delete = findViewById(R.id.delete);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.addteacher("1");
            }
        });
    }

    private void displayData() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No teacher Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext()){
                name.add(cursor.getString(2));
                email.add(cursor.getString(3));
                id.add(cursor.getString(0));

            }
        }
    }


}