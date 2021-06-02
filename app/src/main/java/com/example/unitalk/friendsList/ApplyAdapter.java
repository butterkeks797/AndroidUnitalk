package com.example.unitalk.friendsList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.unitalk.MyDatabaseHelper;
import com.example.unitalk.R;
import com.example.unitalk.bean.User;

import java.util.List;

public class ApplyAdapter extends ArrayAdapter<User> implements View.OnClickListener {

    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;
    private List<User> applyList;
    private int resourceId;
    private int position;

    public ApplyAdapter(Context context, Callback callback, int textViewResourceId, List<User> applyList) {
        super(context, textViewResourceId, applyList);
        resourceId = textViewResourceId;
        this.applyList = applyList;
        this.callback = callback;
    }

    public void setApplyList(List<User> stringList) {
        this.applyList = applyList;
    }

    public List<User> getApplyList() {
        return applyList;
    }

    @Override
    public int getCount() {
        return applyList.size();
    }

    @Override
    public User getItem(int position) {
        return getCount() == 0 ? null : applyList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onClick(View v) {
        callback.click(v);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        User apply = getItem(position);
        View view;
        ViewHolder viewHolder;
        this.position = position;
        if (convertView == null) {
            //任务 补充完整
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.apply_name = (TextView) view.findViewById(R.id.apply_name);
            viewHolder.apply_mother_tongue = (TextView) view.findViewById(R.id.apply_mother_tongue);
            viewHolder.apply_target_language1 = (TextView) view.findViewById(R.id.apply_target_language1);
            viewHolder.apply_target_language2 = (TextView) view.findViewById(R.id.apply_target_language2);
            viewHolder.apply_target_language3 = (TextView) view.findViewById(R.id.apply_target_language3);
            viewHolder.apply_intention = (TextView) view.findViewById(R.id.apply_intention);
            viewHolder.btn_accept = (Button) view.findViewById(R.id.btn_accept);
            viewHolder.btn_ignore = (Button) view.findViewById(R.id.btn_ignore);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.apply_name.setText(apply.getUserName());
        viewHolder.apply_mother_tongue.setText(apply.getMotherTongue());
        viewHolder.apply_target_language1.setText(apply.getTargetLanguage1());
        viewHolder.apply_target_language2.setText(apply.getTargetLanguage2());
        viewHolder.apply_target_language3.setText(apply.getTargetLanguage3());
        viewHolder.apply_intention.setText(apply.getIntention());
        viewHolder.btn_accept.setOnClickListener(this);
        viewHolder.btn_accept.setTag(position);

        return view;
    }

    class ViewHolder {
        TextView apply_name;
        TextView apply_mother_tongue;
        TextView apply_target_language1;
        TextView apply_target_language2;
        TextView apply_target_language3;
        TextView apply_intention;
        Button btn_accept;
        Button btn_ignore;
    }

    private Callback callback;

    public interface Callback {
        public void click(View v);
    }

}
