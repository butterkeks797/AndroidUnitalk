package com.example.unitalk.friendsList.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unitalk.DAO.ApplyDAO;
import com.example.unitalk.DAO.FriendsListDAO;
import com.example.unitalk.R;
import com.example.unitalk.bean.User;
import com.example.unitalk.friendsList.ApplyAdapter;
import com.example.unitalk.friendsList.SortAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class NewFriendActivity extends AppCompatActivity implements ApplyAdapter.Callback {
    private int myId;
    private String friendName;
    private ApplyDAO applyDAO;
    private ListView list_new_friends;
    private ApplyAdapter applyAdapter;
    private List<User> newFriendsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        list_new_friends = (ListView) findViewById(R.id.list_new_friends);
        initData();

        list_new_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Button btn_accept = (Button) view.findViewById(R.id.btn_accept);
                User u = (User) list_new_friends.getItemAtPosition(i);
                Log.d("NewFriendActivity", "now is " + u.getUserName());
                btn_accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        });
    }

    private void initData() {
        // todo 遍历cursor，放入user对象
        //List<User> applyList= applyDAO.query(id);
        // todo 获得id

        applyDAO = new ApplyDAO(this);
        newFriendsList = applyDAO.query(123);
        applyAdapter = new ApplyAdapter(this, this, R.layout.new_friend_item, newFriendsList);
        list_new_friends.setAdapter(applyAdapter);
    }

    @Override
    public void click(View v) {//重写Callback的click方法
        Log.d("NewFriendActivity", "now is ");
    }
}
