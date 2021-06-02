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
import com.example.unitalk.friendsList.activity.ChatRoomActivity;
import com.example.unitalk.friendsList.activity.FriendListActivity;
import com.example.unitalk.partnerMatch.activity.MatchResultActivity;

public class FriendsListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_friendslist, null);

        Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
        intent.putExtra("friendName", "testFriend");
        intent.putExtra("friendId", 3); //TODO
        startActivity(intent);
        return view;
    }
}
