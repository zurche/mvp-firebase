package com.example.zurcher.firebaseexample.chat.model;

import java.net.URL;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatMessage {

    String message;
    String senderUserName;
    String profilePicUri;

    public ChatMessage() {
    }

    public ChatMessage(String message, String senderUserName) {
        this.message = message;
        this.senderUserName = senderUserName;
    }

    public String getProfilePicUri() {
        return profilePicUri;
    }

    public void setProfilePicUri(String profilePicUri) {
        this.profilePicUri = profilePicUri;
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
