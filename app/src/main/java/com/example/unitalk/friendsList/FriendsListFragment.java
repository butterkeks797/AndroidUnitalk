package com.example.unitalk.friendsList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.unitalk.DAO.ApplyDAO;
import com.example.unitalk.DAO.FriendsListDAO;
import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;
import com.example.unitalk.bean.MatchResult;
import com.example.unitalk.bean.User;
import com.example.unitalk.friendsList.activity.ChatRoomActivity;
import com.example.unitalk.friendsList.activity.NewFriendActivity;
import com.example.unitalk.partnerMatch.MatchAdapter;
import com.example.unitalk.partnerMatch.activity.MatchResultActivity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsListFragment extends Fragment {
    private SideBar sideBar;

    private LinearLayout layout_new_friend_bar;
    private ListView list_myFriends;

    private List<User> myFriendsList;
    private FriendsListDAO friendsListDAO;
    private SortAdapter sortAdapter;

    private int friendId;
    private String friendName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_friendslist, null);

        list_myFriends = (ListView) view.findViewById(R.id.list_myFriends);

        sideBar = (SideBar) view.findViewById(R.id.side_bar);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < myFriendsList.size(); i++) {
                    if (selectStr.equalsIgnoreCase(myFriendsList.get(i).getFirstLetter())) {
                        list_myFriends.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });

        initData();

        // 页面上方 “新的语伴”入口
        layout_new_friend_bar = (LinearLayout) view.findViewById(R.id.new_friend_bar);
        layout_new_friend_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewFriendActivity.class);
                startActivity(intent);
            }
        });

        // 语伴列表事件
        list_myFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                friendId = myFriendsList.get(position).getId();
                friendName = myFriendsList.get(position).getUserName();
                Log.d("FriendListFragment", "go to ChatRoomActivity with id of " + friendId);
                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                intent.putExtra("friendName", friendName);
                intent.putExtra("friendId", friendId);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initData() {
        friendsListDAO = new FriendsListDAO(getContext());
        myFriendsList = friendsListDAO.query();
        Collections.sort(myFriendsList); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        sortAdapter = new SortAdapter(getContext(), R.layout.friendlist_item, myFriendsList, false);
        list_myFriends.setAdapter(sortAdapter);
    }

}
