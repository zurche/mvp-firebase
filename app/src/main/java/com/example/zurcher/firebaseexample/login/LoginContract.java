package com.example.zurcher.firebaseexample.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by az on 09/12/16.
 */

public interface LoginContract {
    interface View {

        void startChatListActivity();

        void showFirebaseAuthenticationFailedMessage();
    }

    interface Presenter {

        void logInWithFirebase(GoogleSignInAccount account);
    }
}
