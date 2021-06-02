package com.example.unitalk.friendsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.unitalk.R;
import com.example.unitalk.bean.ChatEntity;

import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatEntity> {
	private int resourceId;

	public ChatMessageAdapter(Context context, int textViewResourceId, List<ChatEntity> chatList) {
		super(context, textViewResourceId, chatList);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatEntity chatEntity = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.chat_friend_left_layout);
			viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.chat_user_right_layout);
			viewHolder.timeView = (TextView) view.findViewById(R.id.message_time);
			viewHolder.leftPhotoView = (ImageView) view.findViewById(R.id.message_friend_avatar);
			viewHolder.rightPhotoView = (ImageView) view.findViewById(R.id.message_user_avatar);
			viewHolder.leftMessageView = (TextView) view.findViewById(R.id.friend_message);
			viewHolder.rightMessageView = (TextView) view.findViewById(R.id.user_message);
			view.setTag(viewHolder);

		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		//User user = ApplicationData.getInstance().getUserInfo();
		viewHolder.timeView.setText(chatEntity.getSendDate());
		if (chatEntity.getMessageType() == ChatEntity.SEND) {
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);

			//viewHolder.rightPhotoView.setImageBitmap(ApplicationData.getInstance().getUserPhoto()); //TODO:获取用户设置的头像
			viewHolder.rightMessageView.setText(chatEntity.getContent());

		} else if (chatEntity.getMessageType() == ChatEntity.RECEIVE) {// 本身作为接收方
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);

			//viewHolder.leftPhotoView.setImageBitmap(ApplicationData.getInstance().getFriendPhotoMap().get(chatEntity.getSenderId())); //TODO:获取用户设置的头像
			viewHolder.leftMessageView.setText(chatEntity.getContent());

		}
		return view;
	}

	class ViewHolder {
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMessageView;
		TextView rightMessageView;
		TextView timeView;
		ImageView leftPhotoView;
		ImageView rightPhotoView;
	}

}

