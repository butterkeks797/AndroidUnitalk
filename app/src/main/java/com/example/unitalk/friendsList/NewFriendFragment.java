package com.example.unitalk.friendsList;

import android.app.AlertDialog;
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
import com.example.unitalk.R;
import com.example.unitalk.bean.User;

import java.util.ArrayList;

public class NewFriendFragment extends Fragment {
    LinearLayout layout_new_friend;
    ApplyDAO applyDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_new_friend, null);
        super.onCreate(savedInstanceState);
        Log.d("NewFriendFragment", "come to new friend");
        listView = (ListView) view.findViewById(R.id.listView);

        initData();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme)
                        .setTitle("提示")
                        .setMessage("已经通过")
                        .show();
                if (id == R.id.btn_accept){


                };
                if (id == R.id.btn_ignore){


                };

                Log.d("NewFriendFragment", "apply: " + position);

            }
        });



        return view;
    }

    private ListView listView;
    private ArrayList<User> list;

    private void initData() {
        int id = 10001; // TODO get login id
        list = new ArrayList<>();
        User u1 = new User("申请", "中文", "英语","法语","西班牙语", "关键词");
        list.add(u1);
        User u2 = new User("申请2", "中文", "英语","法语","西班牙语", "关键词");

        list.add(u2);
        User u3 = new User("申请3", "中文", "英语","法语","西班牙语", "关键词");
        list.add(u3);

        // todo 遍历cursor，放入user对象
        // Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        // applyDAO = new ApplyDAO(getContext());
        // need fix: List<User> applyList = applyDAO.query(id);

        ApplyAdapter applyAdapter = new ApplyAdapter(getContext(), R.layout.new_friend_item, list);
        listView.setAdapter(applyAdapter);
    }
}
