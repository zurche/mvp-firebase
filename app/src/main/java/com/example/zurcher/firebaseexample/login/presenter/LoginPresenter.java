package com.example.zurcher.firebaseexample.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.zurcher.firebaseexample.login.LoginContract;
import com.example.zurcher.firebaseexample.login.view.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by az on 09/12/16.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private final LoginContract.View mView;

    private final FirebaseAuth mAuth;

    private final Context mContext;

    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Log.d(TAG, "User is Signed In");
                mView.startChatListActivity();
            } else {
                Log.d(TAG, "User is Signed Out");
            }
        }
    };

    public LoginPresenter(Context context) {
        mView = (LoginContract.View) context;
        mContext = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void setAuthListener() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void removeAuthListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void logInWithFirebase(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            mView.showFirebaseAuthenticationFailedMessage();

                        } else {
                            mView.startChatListActivity();
                        }
                    }
                });
    }
}
