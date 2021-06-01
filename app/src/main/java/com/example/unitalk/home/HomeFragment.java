package com.example.unitalk.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unitalk.MainActivity;
import com.example.unitalk.R;
import com.example.unitalk.languageTest.activity.ImportQuestions;
import com.example.unitalk.partnerMatch.activity.ImportUsers;

public class HomeFragment extends Fragment {
    Button btn_user_center;
    Button btn_language_test;
    Button btn_partner_match;
    Button btn_friends_list;
    Button btn_import_questions;
    Button btn_import_users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);

        btn_user_center = (Button) view.findViewById(R.id.btn_user_center);
        btn_language_test = (Button) view.findViewById(R.id.btn_language_test);
        btn_partner_match = (Button) view.findViewById(R.id.btn_partner_match);
        btn_friends_list = (Button) view.findViewById(R.id.btn_friends_list);

        btn_user_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        btn_language_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        btn_partner_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });
        btn_friends_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("id",4);
                startActivity(intent);
            }
        });



        btn_import_questions = (Button) view.findViewById(R.id.btn_import_questions);
        btn_import_users = (Button) view.findViewById(R.id.btn_import_users);
        btn_import_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImportQuestions.class);
                startActivity(intent);
            }
        });
        btn_import_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ImportUsers.class);
                startActivity(intent);
            }
        });




        return view;
    }


}
