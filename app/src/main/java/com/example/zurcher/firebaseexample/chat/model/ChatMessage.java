package com.example.zurcher.firebaseexample.chat.model;

/**
 * Created by zurcher on 27/10/16.
 */
public class ChatMessage implements Comparable<ChatMessage> {

    String message;
    String senderUserName;
    String profilePicUri;
    String timestamp;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(ChatMessage another) {
        return another.getTimestamp().compareTo(this.getTimestamp());
    }
}
