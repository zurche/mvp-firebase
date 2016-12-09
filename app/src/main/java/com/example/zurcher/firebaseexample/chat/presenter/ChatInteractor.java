package com.example.zurcher.firebaseexample.chat.presenter;

import com.example.zurcher.firebaseexample.chat.model.ChatMessage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by zurcher on 27/10/16.
 */
class ChatInteractor {

    private static final String TAG = ChatInteractor.class.getSimpleName();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference firebaseReference = database.getReference("chat");

    private ChatPresenter presenter;

    private ArrayList<ChatMessage> mCurrentChatList = new ArrayList<>();

    ChatInteractor(ChatPresenter presenter, int chatId) {
        this.presenter = presenter;
        retrieveCurrentChat();
    }

    private void retrieveCurrentChat() {
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCurrentChatList.clear();

                for (DataSnapshot messagesSnapshot : dataSnapshot.getChildren()) {
                    ChatMessage chatMessage = messagesSnapshot.getValue(ChatMessage.class);
                    mCurrentChatList.add(chatMessage);
                }

                System.out.println(mCurrentChatList);

                presenter.refreshCurrentChatList(mCurrentChatList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: Handle error on presenter here.
            }
        });
    }

    void sendNewMessageToChat(ChatMessage message) {
        String chatKey = String.valueOf(System.currentTimeMillis());
        firebaseReference.child(chatKey).setValue(message);
    }
}
