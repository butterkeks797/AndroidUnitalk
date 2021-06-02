package com.example.unitalk.friendsList.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.unitalk.MyDatabaseHelper;

public class ImportChatSend extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 3);
        dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // 组装数据
        values.put("sender_id", 1);
        values.put("receiver_id", 3);
        values.put("message_type", 1);
        values.put("send_date", "06-01 13:25");
        values.put("content", "hello ^-^");
        db.insert("ChatMessage", null, values); // 插入数据
        values.clear();


    }
}
