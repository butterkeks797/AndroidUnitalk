package com.example.unitalk.friendsList;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unitalk.R;
import com.example.unitalk.bean.User;

import java.util.List;

public class SortAdapter extends BaseAdapter{

    private List<User> list = null;
    private Context mContext;
    private boolean isNewFriend;

    public SortAdapter(Context mContext, List<User> list, boolean isNewFriend) {
        this.mContext = mContext;
        this.list = list;
        this.isNewFriend = isNewFriend;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder;
        final User user = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.friendlist_item, null);
            viewHolder.userName = (TextView) view.findViewById(R.id.name);
            viewHolder.chatDate = (TextView) view.findViewById(R.id.chat_date);
            viewHolder.briefIntro = (TextView) view.findViewById(R.id.brief_intro);
            viewHolder.dots = (TextView) view.findViewById(R.id.dots);
            viewHolder.catalog = (TextView) view.findViewById(R.id.catalog);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取首字母作为目录catalog
        String catalog = list.get(position).getFirstLetter();

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(catalog)){
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(user.getFirstLetter().toUpperCase());
        }else{
            viewHolder.catalog.setVisibility(View.GONE);
        }

        viewHolder.userName.setText(this.list.get(position).getUserName());
        viewHolder.chatDate.setText(this.list.get(position).getChatDate());
        viewHolder.briefIntro.setText(this.list.get(position).getBriefIntro());
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
            String sortStr = list.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }



}