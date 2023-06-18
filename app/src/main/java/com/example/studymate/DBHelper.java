package com.example.studymate;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class DBHelper extends SQLiteOpenHelper {


    public static final String databaseName = "SignLog.db";

    public DBHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT, fullname TEXT)");
        MyDatabase.execSQL("CREATE TABLE teacher (teacherid TEXT PRIMARY KEY, Tpassword TEXT, Tfullname TEXT, Temail TEXT, status INTEGER DEFAULT 0)");
        MyDatabase.execSQL("CREATE TABLE admin (adminID TEXT PRIMARY KEY, password TEXT)");
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

    public boolean checkTeacherLoginCredentials(String id, String password, String name,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM teacher WHERE teacherid = ? AND Tpassword = ? AND Tfullname = ? AND status = ?", new String[]{id, password, name,status});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }




    public boolean insertAdminData(String adminId, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if admin data already exists
        Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE adminID = ?", new String[]{adminId});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false; // Admin data already exists, return false
        }
        cursor.close();

        ContentValues contentValues = new ContentValues();
        contentValues.put("adminID", adminId);
        contentValues.put("password", password);
        long result = db.insert("admin", null, contentValues);
        return result != -1;
    }





    public boolean checkAdminLoginCredentials(String adminId, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE adminID = ? AND password = ?", new String[]{adminId, password});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }





    public boolean deleteteacher(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("teacher", "teacherID=?", new String[]{id});
        return result > 0;
    }

    public boolean addteacher(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", 1); // Change the status to 1
        int result = db.update("teacher", values, "teacherID=?", new String[]{id});
        return result > 0;
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from teacher",null);
        return cursor;
    }

    @SuppressLint("Range")
    public int checkTeacherStatus(String teacherId) {
        SQLiteDatabase db = this.getReadableDatabase();
        int status = -1;

        // Query to fetch the status of the teacher with the given teacherId
        String query = "SELECT status FROM teacher WHERE teacherid = ?";
        Cursor cursor = db.rawQuery(query, new String[]{teacherId});

        if (cursor.moveToFirst()) {
            status = cursor.getInt(cursor.getColumnIndex("status"));
        }

        cursor.close();
        db.close();

        return status;
    }
}