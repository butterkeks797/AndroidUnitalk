package com.example.unitalk.friendsList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unitalk.R;
import com.example.unitalk.bean.MatchResult;
import com.example.unitalk.bean.User;
import com.example.unitalk.partnerMatch.MatchAdapter;

import java.util.List;

public class SortAdapter extends ArrayAdapter<User> {

    private List<User> friendsList = null;
    //private Context mContext;
    private boolean isNewFriend;
    private int resourceId;


    public SortAdapter(Context context, int textViewResourceId, List<User> myFriendsList, boolean isNewFriend) {
        super(context, textViewResourceId, myFriendsList);
        resourceId = textViewResourceId;
        this.isNewFriend = isNewFriend;
        friendsList = myFriendsList;
    }


    public int getCount() {
        return friendsList.size();
    }

    public User getItem(int position) {
        return friendsList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User friend = getItem(position);
        View view;
        ViewHolder viewHolder;
        //final User user = list.get(position);
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.Avatar);
            viewHolder.userName = (TextView) view.findViewById(R.id.name);
            viewHolder.chatDate = (TextView) view.findViewById(R.id.chat_date);
            viewHolder.briefIntro = (TextView) view.findViewById(R.id.brief_intro);
            viewHolder.dots = (TextView) view.findViewById(R.id.dots);
            viewHolder.catalog = (TextView) view.findViewById(R.id.catalog);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取首字母作为目录catalog
        String catalog = friendsList.get(position).getFirstLetter();

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalog)){
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(friend.getFirstLetter().toUpperCase());
        }else{
            viewHolder.catalog.setVisibility(View.GONE);
        }

        viewHolder.icon.setImageResource(this.friendsList.get(position).getIconType());
        viewHolder.userName.setText(this.friendsList.get(position).getUserName());
        viewHolder.chatDate.setText(this.friendsList.get(position).getChatDate());
        viewHolder.briefIntro.setText(this.friendsList.get(position).getBriefIntro());
        viewHolder.dots.setText("...");
        if (isNewFriend){
            viewHolder.catalog.setVisibility(View.GONE);
            viewHolder.dots.setVisibility(View.GONE);
        }
        return view;

    }

    final static class ViewHolder {
        TextView catalog;
        TextView userName;
        TextView chatDate;
        TextView briefIntro;
        ImageView icon;
        TextView dots;
    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = friendsList.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }



}