package com.example.unitalk.languageTest.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.unitalk.MyDatabaseHelper;

import static com.example.unitalk.MyDatabaseHelper.CREATE_USER_INFO;

public class ImportQuestions  extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 2);
        dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("language", "英语");
        values.put("level", "初级");
        values.put("category", "dsad");
        values.put("type", 1);
        values.put("question", "问题内容问题内容问题内容问题内容");
        values.put("answer", "A");
        values.put("points", 10);
        values.put("choiceA", "选项内容（答案）");
        values.put("choiceB", "选项内容");
        values.put("choiceC", "选项内容");
        values.put("choiceD", "选项内容");

        db.insert("Question", null, values); // 插入第一条数据
        values.clear();

    }
}
