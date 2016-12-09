package com.example.zurcher.firebaseexample.chat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zurcher.firebaseexample.R;
import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.presenter.ChatPresenter;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private static final String TAG = ChatActivity.class.getSimpleName();

    private ChatPresenter presenter;

    @BindView(R.id.chat_list)
    RecyclerView chat_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new ChatPresenter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        chat_list.setLayoutManager(llm);
    }

    @Override
    public void refreshCurrentChatList(ArrayList<ChatMessage> currentChatMessage) {
        ChatListAdapter chatListAdapter = new ChatListAdapter(currentChatMessage);
        chat_list.setAdapter(chatListAdapter);
    }

    @OnClick(R.id.new_message_button)
    void sendNewMessageToChat(View view) {
        ChatMessage chatMessage = new ChatMessage("Message is very long", "12354", "Alejandro Zurcher");
        presenter.sendNewMessage(chatMessage);
    }
}
