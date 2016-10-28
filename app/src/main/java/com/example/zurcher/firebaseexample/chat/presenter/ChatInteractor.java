package com.example.zurcher.firebaseexample.chat.presenter;

import android.util.Log;

import com.example.zurcher.firebaseexample.chat.model.Chat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatInteractor {

    private static final String TAG = ChatInteractor.class.getSimpleName();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference firebaseReference = database.getReference("chat");

    private ChatPresenter presenter;

    private Chat currentChat;

    ChatInteractor(ChatPresenter presenter, int chatId) {
        this.presenter = presenter;
        this.currentChat = new Chat(new ArrayList<String>(), chatId);
        retrieveCurrentChat();
    }

    private void retrieveCurrentChat() {
        firebaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Chat selectedChat = dataSnapshot.child(String.valueOf(currentChat.getChatId())).getValue(Chat.class);
                if (null != selectedChat) {
                    currentChat = selectedChat;
                    Log.d(TAG, "Chat is: " + currentChat.getMessages());
                } else {
                    Log.e(TAG, "Chat list is empty");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: Handle error on presenter here.
            }
        });
    }

    public void sendNewMessageToChat(String message) {
        currentChat.getMessages().add(message);

        firebaseReference.child(String.valueOf(currentChat.getChatId())).setValue(currentChat);

        presenter.refreshCurrentChatMessages(currentChat);
    }
}
