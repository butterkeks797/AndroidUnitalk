package com.example.unitalk.friendsList.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.unitalk.DAO.ChatDAO;
import com.example.unitalk.DAO.MatchDAO;
import com.example.unitalk.R;
import com.example.unitalk.bean.ChatEntity;
import com.example.unitalk.bean.MatchResult;
import com.example.unitalk.friendsList.ChatMessageAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    private ChatDAO chatDAO;

    //private TitleBarView mTitleBarView;
    private int myId;
    private int friendId;
    private String friendName;

    private ListView list_chat_message;
    private RecyclerView rv_chatList;
    private ChatMessageAdapter chatMessageAdapter;

    private Button btn_chat_send;
    private ImageButton btn_chat_emotion;
    private EditText edit_chat_input;
    private List<ChatEntity> chatList;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat_room);

        Intent intent = getIntent();
        friendName = intent.getStringExtra("friendName"); //TODO
        friendId = intent.getIntExtra("friendId", 0);
        Log.d("friendId", String.valueOf(friendId));
        Log.d("friendName", friendName);

        initViews();
        initEvents();
    }

    protected void initViews() {
        //TODO: TitleBar
        //mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
        //mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        //mTitleBarView.setTitleText("与" + friendName + "对话");
        list_chat_message = (ListView) findViewById(R.id.list_chat_message);
        //rv_chatList = (RecyclerView) findViewById(R.id.rv_chatList);
        btn_chat_send = (Button) findViewById(R.id.btn_chat_send);
        //btn_chat_emotion = (ImageButton) findViewById(R.id.btn_chat_emotion); //TODO
        edit_chat_input = (EditText) findViewById(R.id.edit_chat_input);
    }


    protected void initEvents() {
        /*
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        chatMessageAdapter.notifyDataSetChanged();
                        list_chat_message.setSelection(chatList.size());
                        break;
                    default:
                        break;
                }
            }
        };
         */

        //ApplicationData.getInstance().setChatHandler(handler);
        //chatList = ApplicationData.getInstance().getChatMessagesMap().get(friendId);
        //TODO: 双向

        myId = 1;
        chatDAO = new ChatDAO(this);
        chatList = chatDAO.query(friendId, myId);
        /*
        if(chatList == null){
            chatList = ImDB.getInstance(ChatRoomActivity.this).getChatMessage(friendId);
            ApplicationData.getInstance().getChatMessagesMap().put(friendId, chatList);
        }

         */
        chatMessageAdapter = new ChatMessageAdapter(this, R.layout.chat_message_item_, chatList);
        list_chat_message.setAdapter(chatMessageAdapter);


        // Send
        btn_chat_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String content = edit_chat_input.getText().toString();
                edit_chat_input.setText("");

                ChatEntity chatMessage = new ChatEntity();
                chatMessage.setContent(content);
                chatMessage.setSenderId(myId);
                chatMessage.setReceiverId(friendId);
                chatMessage.setMessageType(ChatEntity.SEND);
                Date date = new Date(); //获取系统当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm");
                String sendTime = sdf.format(date);
                chatMessage.setSendDate(sendTime);

                chatList.add(chatMessage);
                chatMessageAdapter.notifyDataSetChanged();
                list_chat_message.setSelection(chatList.size());

                /*
                UserAction.sendMessage(chatMessage);
                ImDB.getInstance(ChatRoomActivity.this)
                        .saveChatMessage(chatMessage);
                 */

                // 将发送的消息写入ChatMessage表
                chatDAO.insert(chatMessage);


            }
        });
    }




}
