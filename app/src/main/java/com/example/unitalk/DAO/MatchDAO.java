package com.example.unitalk.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.bean.MatchResult;

import java.util.ArrayList;
import java.util.List;

public class MatchDAO {

    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public MatchDAO(Context context){
        dbHelper = new MyDatabaseHelper(context, "Unitalk.db", null, 3);
    }


    public List<MatchResult> query(String lang){
        List<MatchResult> partnerList = new ArrayList<MatchResult>();

        db = dbHelper.getReadableDatabase();

        Log.d("lang", lang);

        //sql:sql语句，  selectionArgs:查询条件占位符的值,返回一个cursor对象
        Cursor cursor = db.rawQuery("select id, username, gender, school, mother_tongue, " +
                        "target_language1, target_language2, target_language3, intention from UserInfo " +
                        "where mother_tongue=? or target_language1=? or target_language2=? or target_language3=?",
                new String []{lang, lang, lang, lang});

        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
            //循环遍历结果集，获取每一行的内容
            int i=0;
            while (cursor.moveToNext() && i<5){//条件，游标能否定位到下一行
                //获取数据
                int id = cursor.getInt(0);
                if (id==1) {
                    continue;
                }

                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String school = cursor.getString(3);
                String mother_tongue = cursor.getString(4);
                String target_language1 = cursor.getString(5);
                String target_language2 = cursor.getString(6);
                String target_language3 = cursor.getString(7);
                String intention = cursor.getString(8);
                System.out.println("Selected info: id="+id+";name="+name);

                MatchResult ptn = new MatchResult(id, name, gender, school, mother_tongue,
                        target_language1, target_language2, target_language3, intention);
                partnerList.add(ptn);

                i++;
            }
            cursor.close();//关闭结果集
        }

        db.close();

        return partnerList;

    }
}
