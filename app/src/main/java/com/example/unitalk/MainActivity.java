package com.example.unitalk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.unitalk.R;
import com.example.unitalk.languageTest.TestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private BottomBar bottomBar;
    //private BottomBarTab tab;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button language_test;


    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();


        dbHelper = new MyDatabaseHelper(this, "Unitalk.db", null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
            }
        });
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"500"});
            }
        });

    }

    private void initViews() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        language_test.setOnClickListener(this);
    }

    private void findViews() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        language_test = (Button) findViewById(R.id.language_test);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, SampleActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, BottomBarColorActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, BadgesActivity.class));
                break;
            case R.id.language_test:
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;

        }

    }
}
