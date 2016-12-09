package com.example.zurcher.firebaseexample.chat.model;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatMessage {

    String message;
    String senderUserName;

    public ChatMessage() {
    }

    public ChatMessage(String message, String senderUserName) {
        this.message = message;
        this.senderUserName = senderUserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }
}
