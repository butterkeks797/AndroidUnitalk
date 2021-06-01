package com.example.unitalk.friendsList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unitalk.R;
import com.example.unitalk.friendsList.activity.FriendListActivity;

public class FriendsListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_friendslist, null);
        startActivity(new Intent(getActivity(), FriendListActivity.class));
        return view;
    }
}
