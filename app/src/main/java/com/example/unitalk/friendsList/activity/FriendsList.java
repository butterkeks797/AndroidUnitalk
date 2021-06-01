package com.example.unitalk.friendsList.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;
import com.example.unitalk.UserInfo;
import com.example.unitalk.languageTest.importQuestions.Question;

import java.util.ArrayList;
import java.util.List;

public class FriendsList extends AppCompatActivity {
    int pagesize;
    private MyDatabaseHelper dbHelper;

    private List<UserInfo> friendList;


    private UserInfo user;

    private Button btn_rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("FriendListActivity", "entered.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_test);
        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 2);
        // initLanguageTest();
        // initUIComponents();
        pagesize = 10;
        friendList = getFriendsPaged(pagesize);


        btn_rate = (Button) findViewById(R.id.btn_rate);
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public List<UserInfo> getFriendsPaged(int pagesize) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 查询Question表中所有的数据
        String[] selectionArgs = { String.valueOf(user.getId()) };
        Cursor cursor = db.query("UserInfo", null, "id=?", selectionArgs, null, null, null);
        int i = 0;
        List<UserInfo> res = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                UserInfo q = new UserInfo();
                q.setId(cursor.getInt(cursor.getColumnIndex("id")));
                q.setPartnerList(cursor.getString(cursor.getColumnIndex("partners")));
                res.add(q); // 取出信息
                Log.d("FriendListActivity", "got question " + i);
                i++;
            } while (cursor.moveToNext() && i <pagesize);
        }
        Log.d("FriendListActivity", "total count is " + i);
        cursor.close();
        return res;
    }

}
