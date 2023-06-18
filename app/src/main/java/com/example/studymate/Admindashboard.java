package com.example.studymate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Admindashboard extends AppCompatActivity implements MyAdapter.ButtonClickListener {
    RecyclerView recyclerView;
    ArrayList<String> name,email,id;
    DBHelper DB;
    MyAdapter adapter;
    Button approve,delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        id = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, name, email, id, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();





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
    @Override
    public void onApproveClick(int position) {
        String teacherId = id.get(position);
        DB.addteacher(teacherId);
        refreshData();
        Toast.makeText(this, "Teacher approved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        String teacherId = id.get(position);
        DB.deleteteacher(teacherId);
        refreshData();
        Toast.makeText(this, "Teacher deleted", Toast.LENGTH_SHORT).show();
    }


    private void refreshData() {
        name.clear();
        email.clear();
        id.clear();
        displayData();
        adapter.notifyDataSetChanged();
    }

}