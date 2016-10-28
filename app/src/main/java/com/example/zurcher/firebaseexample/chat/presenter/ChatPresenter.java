package com.example.zurcher.firebaseexample.chat.presenter;

import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.model.Chat;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View view;

    private ChatInteractor interactor;

    public ChatPresenter(final ChatContract.View view) {
        this.view = view;
        //TODO: Number 3 is hardcoded but a mechanism to retrieve a specific chat is required to
        //TODO: determine the ID of the required chat.
        interactor = new ChatInteractor(this, 3);
    }

    @Override
    public void sendNewMessage(String message) {
        interactor.sendNewMessageToChat(message);
    }

    @Override
    public void refreshCurrentChatMessages(Chat currentChat) {
        view.refreshChatMessagesList(currentChat);
    }
}
