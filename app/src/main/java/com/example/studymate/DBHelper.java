package com.example.studymate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String databaseName = "SignLog.db";

    public DBHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT, fullname TEXT)");
        MyDatabase.execSQL("create Table teacher(teacherid TEXT primary key, Tpassword TEXT, Tfullname TEXT,Temail TEXT,status  INTEGER DEFAULT 0)");
        MyDatabase.execSQL("create Table admin(adminID TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists teacher");
        MyDB.execSQL("drop Table if exists admin");
    }

    public Boolean insertData(String fullname,String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("fullname", fullname);
        long result = MyDatabase.insert("users", null, contentValues);

        return result != -1;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkLoginCredentials(String name, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE fullname = ? AND password = ?", new String[]{name, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertTeacherData(String teacherId, String email, String password, String fullname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("teacherID", teacherId);
        contentValues.put("Temail", email);
        contentValues.put("Tpassword", password);
        contentValues.put("Tfullname", fullname);
        long result = db.insert("teacher", null, contentValues);
        return result != -1;
    }

    public boolean checkTeacherLoginCredentials(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE Tfullname = ? AND Tpassword = ?", new String[]{name, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
}}