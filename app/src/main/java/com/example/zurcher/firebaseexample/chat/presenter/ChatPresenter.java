package com.example.zurcher.firebaseexample.chat.presenter;

import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View mView;

    private ChatInteractor interactor;

    public ChatPresenter(final ChatContract.View view) {
        mView = view;
        interactor = new ChatInteractor(this);
    }

    @Override
    public void sendNewMessage(ChatMessage message) {
        interactor.sendNewMessageToChat(message);
    }

    @Override
    public void refreshCurrentChatList(ArrayList<ChatMessage> currentChatMessage) {
        mView.refreshCurrentChatList(currentChatMessage);
    }

    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        mView.closeChat();
    }
}
