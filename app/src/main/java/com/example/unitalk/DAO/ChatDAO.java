package com.example.unitalk.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.bean.ChatEntity;
import com.example.unitalk.bean.MatchResult;

import java.util.ArrayList;
import java.util.List;

public class ChatDAO {

    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;

    public ChatDAO(Context context){
        dbHelper = new MyDatabaseHelper(context, "Unitalk.db", null, 3);
    }


    public List<ChatEntity> query(int friendId, int myId){
        List<ChatEntity> chatList = new ArrayList<ChatEntity>();

        Log.d("friendId", String.valueOf(friendId));
        Log.d("myId", String.valueOf(myId));

        db = dbHelper.getReadableDatabase();

        Log.d("friendId", String.valueOf(friendId));
        Log.d("myId", String.valueOf(myId));

        //sql:sql语句，  selectionArgs:查询条件占位符的值,返回一个cursor对象
        Cursor cursor = db.rawQuery("select id, sender_id, receiver_id, message_type, " +
                        "send_date, content from ChatMessage " +
                        "where sender_id=? and receiver_id=?",
                new String []{String.valueOf(friendId), String.valueOf(myId)});

        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                int id = cursor.getInt(0);
                int senderId = cursor.getInt(1);
                int receiverId = cursor.getInt(2);
                int messageType = cursor.getInt(3);
                String sendDate = cursor.getString(4);
                String content = cursor.getString(5);

                System.out.println("Selected chat message: senderId="+senderId+
                        "; receiverId="+receiverId+"; messageType="+messageType);

                ChatEntity chatEntity = new ChatEntity(senderId, receiverId, messageType, sendDate, content);
                chatList.add(chatEntity);

            }
            cursor.close();//关闭结果集
        }

        db.close();

        return chatList;

    }


    //TODO: 发送消息 = 写入数据库
    public void insert (ChatEntity chatMessage) {
        db = dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        int myId = chatMessage.getSenderId();
        int friendId = chatMessage.getReceiverId();
        int messageType = chatMessage.getMessageType();
        String sendDate = chatMessage.getSendDate();
        String content = chatMessage.getContent();

        Log.d("friendId", String.valueOf(friendId));
        Log.d("myId", String.valueOf(myId));
        Log.d("messageType", String.valueOf(messageType));
        Log.d("sendDate", sendDate);
        Log.d("content", content);

        values.put("sender_id", myId);
        values.put("receiver_id", friendId);
        values.put("message_type", messageType);
        values.put("send_date", sendDate);
        values.put("content", content);

        db.insert("ChatMessage", null, values); // 插入第一条数据
        values.clear();

    }



}
