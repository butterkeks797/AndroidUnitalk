package com.example.unitalk.friendsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.unitalk.R;
import com.example.unitalk.bean.User;
import com.example.unitalk.friendsList.activity.ChatRoomActivity;
import com.example.unitalk.friendsList.activity.NewFriendActivity;

import java.util.ArrayList;
import java.util.Collections;

public class NewFriendFragment extends Fragment {
    LinearLayout layout_new_friend;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_new_friend, null);
        super.onCreate(savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listView);

        initData();
        layout_new_friend = (LinearLayout) view.findViewById(R.id.new_friend_bar);

        layout_new_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewFriendActivity.class));
            }
        });


       //  TextView dots = (TextView) listView.findViewById(R.id.dots);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ChatRoomActivity.class));
            }
        });

        return view;
    }

    private ListView listView;
    private ArrayList<User> list;

    private void initData() {
        list = new ArrayList<>();
        User u1 = new User("申请", "5-23", "英语，西班牙语，法语", "miemiezi");
        list.add(u1);
        User u2 = new User("工具人", "3-21", "英语，德语", "gongjuren");

        list.add(u2);
        User u3 = new User("default", "1.29", "briefIntro", "default");
        list.add(u3);
        list.add(u3);
        list.add(u3);
        list.add(u3);

        // todo 遍历cursor，放入user对象
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }
}
