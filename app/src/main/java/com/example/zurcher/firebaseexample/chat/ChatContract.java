package com.example.zurcher.firebaseexample.chat;

import com.example.zurcher.firebaseexample.chat.model.Chat;

/**
 * Created by zurcher on 27/10/16.
 */
public interface ChatContract {

    interface View {
        void refreshChatMessagesList(Chat currentChat);
    }

    interface Presenter {
        void sendNewMessage(String message);

        void refreshCurrentChatMessages(Chat currentChat);
    }
}
