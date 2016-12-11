package com.example.zurcher.firebaseexample.chat.presenter;

import com.example.zurcher.firebaseexample.chat.ChatContract;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;

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
    public void sendNewMessage(String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(message);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            chatMessage.setSenderUserName(user.getDisplayName());
        } else {
            mView.showUnloggedUserError();
        }

        String profilePicUrl = "https://" + user.getPhotoUrl().getAuthority() + user.getPhotoUrl().getPath();
        chatMessage.setProfilePicUri(profilePicUrl.trim());

        chatMessage.setTimestamp(String.valueOf(System.currentTimeMillis()));

        interactor.sendNewMessageToChat(chatMessage);
    }

    @Override
    public void refreshCurrentChatList(ArrayList<ChatMessage> currentChatMessage) {
        Collections.sort(currentChatMessage);
        mView.refreshCurrentChatList(currentChatMessage);
    }

    /**
     * This code should be used to log out from GMS too:
     * Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
        @Override
        public void onResult(@NonNull Status status) {
            Toast.makeText(LoginActivity.this, "Successfully Signed Out", Toast.LENGTH_SHORT).show();
        }
       });
     */
    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        mView.closeChat();
    }
}
