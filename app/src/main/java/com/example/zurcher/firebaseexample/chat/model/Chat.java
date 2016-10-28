package com.example.zurcher.firebaseexample.chat.model;

import java.util.ArrayList;

/**
 * Created by zurcher on 27/10/16.
 */
public class Chat {

    ArrayList<String> messages;
    int chatId;

    public Chat() {
    }

    public Chat(ArrayList<String> messages, int chatId) {
        this.messages = messages;
        this.chatId = chatId;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
