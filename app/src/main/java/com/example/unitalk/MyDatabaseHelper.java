package com.example.unitalk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    // TODO 用户信息表；题库表；聊天消息表

    public static final String CREATE_QUESTION = "create table Question ("
            + "id integer primary key autoincrement, "
            + "language text, "
            + "level text, "
            + "category text, "
            + "type int, " // 枚举，单选=1、多选=2、判断=3
            + "question text, "
            + "answer text, "// test
            + "points int, "
            + "choiceA text, "
            + "choiceB text, "
            + "choiceC text, "
            + "choiceD text)";

    public static final String CREATE_USER_INFO = "create table UserInfo ("
            + "id integer primary key autoincrement, "
            + "age integer, "
            + "email text, "
            + "region text, "
            + "target_language text, "
            + "major text, "
            + "nationality text, "
            + "school text, "
            + "username text, "
            + "gender text, "
            + "grade text, "
            + "mother_tongue text, "
            + "student_number text, "
            + "grade text, "
            + "mother_tongue text)";



    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUESTION);
        db.execSQL(CREATE_USER_INFO);
       // Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Question");
        db.execSQL("drop table if exists UserInfo");
        onCreate(db);
    }

}
