package com.example.unitalk.languageTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unitalk.R;
import com.example.unitalk.languageTest.activity.LanguageTestActivity;

public class LanguageTestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_languagetest, null);
        Intent intent = new Intent(getActivity(), LanguageTestActivity.class);
        startActivity(intent);
        return view;
    }
}
