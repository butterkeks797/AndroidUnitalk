package com.example.unitalk.partnerMatch.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.unitalk.MyDatabaseHelper;

public class ImportUsers extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 3);
        dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("age", 20);
        values.put("email", "123@123.com");
        values.put("region", "上海");
        values.put("target_language1", "英语");
        values.put("target_language2", "西班牙语");
        values.put("target_language3", "日语");
        values.put("intention", "准备考试");
        values.put("major", "软件工程");
        values.put("nationality", "中国");
        values.put("school", "ECNU");
        values.put("username", "testUser");
        values.put("gender", "男");
        values.put("grade", "大三");
        values.put("student_number", "123123123123");
        values.put("mother_tongue", "汉语");

        db.insert("UserInfo", null, values); // 插入第一条数据
        values.clear();
    }
}

