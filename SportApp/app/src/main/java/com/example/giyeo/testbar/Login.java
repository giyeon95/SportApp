package com.example.giyeo.testbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

public class Login extends AppCompatActivity {
    SessionCallback callback;
    private final String TAG = "kakoLogin";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
    }

    private void redirectSignupActivity(UserProfile userProfile) {

        UserStatus.setUserName(userProfile.getNickname());
        UserStatus.setUserId(userProfile.getId());
        UserStatus.setUserImagePath(userProfile.getProfileImagePath());
        Intent intent = new  Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        Log.e(TAG,"kakao Login Error!!!");
                    } else {
                        //redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                 Log.e(TAG,userProfile.toString());

                    redirectSignupActivity(userProfile);
                }
            });

        }
        @Override
        public void onSessionOpenFailed(KakaoException exception) {

        }

    }

}