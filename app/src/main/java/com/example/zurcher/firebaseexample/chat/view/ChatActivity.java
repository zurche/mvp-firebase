package com.example.zurcher.firebaseexample.chat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zurcher.firebaseexample.R;
import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;
import com.example.zurcher.firebaseexample.chat.presenter.ChatPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatContract.View {

    private static final String TAG = ChatActivity.class.getSimpleName();

    private ChatPresenter presenter;

    @BindView(R.id.chat_list)
    RecyclerView chat_list;

    @BindView(R.id.input_message)
    EditText input_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new ChatPresenter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        chat_list.setLayoutManager(llm);

        input_message.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendNewMessageToChat(v.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });

    }

    @Override
    public void refreshCurrentChatList(ArrayList<ChatMessage> currentChatMessage) {
        ChatListAdapter chatListAdapter = new ChatListAdapter(currentChatMessage);
        chat_list.setAdapter(chatListAdapter);
    }

    void sendNewMessageToChat(String message) {
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setMessage(message);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            chatMessage.setSenderUserName(user.getDisplayName());
        } else {
            // Un-logged user is trying to send a message.
        }

        presenter.sendNewMessage(chatMessage);
    }
}
