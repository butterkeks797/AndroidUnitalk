package com.example.unitalk.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.bean.MatchResult;
import com.example.unitalk.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FriendsListDAO {

    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public FriendsListDAO(Context context) {
        dbHelper = new MyDatabaseHelper(context, "Unitalk.db", null, 3);
    }

    public List<User> query() {
        List<User> friendsList = new ArrayList<User>();

        db = dbHelper.getReadableDatabase();
        //sql:sql语句
        Cursor cursor = db.rawQuery("select id, username, gender, school, mother_tongue, " +
                        "target_language1, target_language2, target_language3, intention from UserInfo " +
                        "where id=3 or id=4 or id=5 or id=6 or id=7", null);

        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                User u = new User();
                u.setId(cursor.getInt(0));
                u.setUserName(cursor.getString(1));
                u.setGender(cursor.getString(2));
                u.setSchool(cursor.getString(3));
                u.setMotherTongue(cursor.getString(4));
                u.setTargetLanguage1(cursor.getString(5));
                u.setTargetLanguage2(cursor.getString(6));
                u.setTargetLanguage3(cursor.getString(7));
                u.setIntention(cursor.getString(8));
                u.setPinyinAndFirstLetter(u.getUserName());
                u.setIconType(u.getUserName());
                System.out.println("Selected friend: id="+u.getId());

                friendsList.add(u);
            }
            cursor.close();//关闭结果集
        }

        db.close();

        return friendsList;

    }




}
