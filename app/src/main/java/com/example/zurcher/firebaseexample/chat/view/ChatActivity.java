package com.example.zurcher.firebaseexample.chat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.zurcher.firebaseexample.R;
import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.presenter.ChatPresenter;
import com.example.zurcher.firebaseexample.chat.model.Chat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private static final String TAG = ChatActivity.class.getSimpleName();

    private ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new ChatPresenter(this);
    }

    @Override
    public void refreshChatMessagesList(Chat currentChat) {
        Log.d(TAG, "Chat Refreshed: " + currentChat.getMessages());
        for (String message :
                currentChat.getMessages()) {
            Log.d(TAG, "Message: " + message);
        }
    }

    @OnClick(R.id.new_message_button)
    void sendNewMessageToChat(View view) {
        presenter.sendNewMessage("SUP!");
    }
}
