package com.example.unitalk.languageTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unitalk.BadgesActivity;
import com.example.unitalk.BottomBarColorActivity;
import com.example.unitalk.MainActivity;
import com.example.unitalk.R;
import com.example.unitalk.SampleActivity;
import com.example.unitalk.languageTest.activity.LanguageTestActivity;

public class LanguageTestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_languagetest, null);
        Button btn_next;

        btn_next = (Button) view.findViewById(R.id.start_test);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LanguageTestActivity.class));

            }
        });

        return view;
    }
}