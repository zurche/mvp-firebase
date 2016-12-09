package com.example.zurcher.firebaseexample.chat.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.zurcher.firebaseexample.R;
import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;
import com.example.zurcher.firebaseexample.chat.presenter.ChatPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                presenter.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void closeChat() {
        finish();
    }

    @OnEditorAction (R.id.input_message)
    protected boolean passwordEditorAction(int actionId) {
        boolean handled = false;
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            sendNewMessageToChat(input_message.getText().toString());
            handled = true;
        }
        return handled;
    }

    @Override
    public void refreshCurrentChatList(ArrayList<ChatMessage> currentChatMessage) {
        ChatListAdapter chatListAdapter = new ChatListAdapter(currentChatMessage);
        chat_list.setAdapter(chatListAdapter);

        hideSoftInput();
        input_message.setText("");
    }

    private void hideSoftInput() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
