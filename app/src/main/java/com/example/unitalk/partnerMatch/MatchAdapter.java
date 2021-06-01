package com.example.unitalk.partnerMatch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;
import com.example.unitalk.bean.MatchResult;

import java.util.List;

public class MatchAdapter extends ArrayAdapter<MatchResult> {

    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    private List<MatchResult> partnerList;
    private int resourceId;

    public MatchAdapter(Context context, int textViewResourceId, List<MatchResult> partnerList) {
        super(context, textViewResourceId, partnerList);
        resourceId = textViewResourceId;
    }

/*
    public void query(String lang){
        dbHelper = new MyDatabaseHelper(getContext(), "Unitalk.db", null, 2);
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
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                int id = cursor.getInt(0);
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

            }
            cursor.close();//关闭结果集
        }

        db.close();

    }

 */


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        MatchResult ptn= getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            //任务 补充完整
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.ptn_name = (TextView) view.findViewById(R.id.ptn_name);
            viewHolder.ptn_gender = (TextView) view.findViewById(R.id.ptn_gender);
            viewHolder.ptn_school = (TextView) view.findViewById(R.id.ptn_school);
            viewHolder.ptn_mother_tongue = (TextView) view.findViewById(R.id.ptn_mother_tongue);
            viewHolder.ptn_target_language1 = (TextView) view.findViewById(R.id.ptn_target_language1);
            viewHolder.ptn_target_language2 = (TextView) view.findViewById(R.id.ptn_target_language2);
            viewHolder.ptn_target_language3 = (TextView) view.findViewById(R.id.ptn_target_language3);
            viewHolder.ptn_intention = (TextView) view.findViewById(R.id.ptn_intention);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ptn_name.setText(ptn.getUsername());
        viewHolder.ptn_gender.setText(ptn.getGender());
        viewHolder.ptn_school.setText(ptn.getSchool());
        viewHolder.ptn_mother_tongue.setText(ptn.getMother_tongue());
        viewHolder.ptn_target_language1.setText(ptn.getTarget_language1());
        viewHolder.ptn_target_language2.setText(ptn.getTarget_language2());
        viewHolder.ptn_target_language3.setText(ptn.getTarget_language3());
        viewHolder.ptn_intention.setText(ptn.getIntention());

        return view;

    }

    class ViewHolder {
        TextView ptn_name;
        TextView ptn_gender;
        TextView ptn_school;
        TextView ptn_mother_tongue;
        TextView ptn_target_language1;
        TextView ptn_target_language2;
        TextView ptn_target_language3;
        TextView ptn_intention;
    }
}
