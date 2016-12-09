package com.example.zurcher.firebaseexample.chat.presenter;

import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;

import java.util.ArrayList;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View view;

    private ChatInteractor interactor;

    public ChatPresenter(final ChatContract.View view) {
        this.view = view;
        interactor = new ChatInteractor(this);
    }

    @Override
    public void sendNewMessage(ChatMessage message) {
        interactor.sendNewMessageToChat(message);
    }

    @Override
    public void refreshCurrentChatList(ArrayList<ChatMessage> currentChatMessage) {
        view.refreshCurrentChatList(currentChatMessage);
    }
}
