package com.example.unitalk.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.bean.User;

import java.util.ArrayList;
import java.util.List;

public class LanguageTestDAO {

    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public LanguageTestDAO(Context context){
        dbHelper = new MyDatabaseHelper(context, "Unitalk.db", null, 3);
    }


    public void update(int queryId, int score){
        List<User> applyList = new ArrayList<>();
        db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("score", score);
        db.update("UserInfo", values,"select id, username, gender, school, mother_tongue, " +
                "target_language1, target_language2, target_language3, intention from UserInfo " +
                "where id=3", null );

        values.clear();
        db.close();
    }
}
