package com.centura.mcontacts.activity.loginActivity;

import com.centura.mcontacts.DataSource.room.EntityModels.User;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
interface LoginContract {

    interface Model {

        void insertUser(User user);

        User getUser(String emailId);

        void storeLoggedIn(boolean isLoggedIn, String mailId);
    }

    interface View {

        void onClicks();

        void setEmailError(String message);

        void setPasswordError(String message);

        void onSignInSuccess(User user);

        void showSignUpDialog();

        void showSignUpEmailError(String message);

        void showSignUpNameError(String message);

        void showSignUpPasswordError(String message);

        void showSignUpConfirmPassError(String message);

        void onSignUpSuccess();

    }

    interface Presenter {
        void onSignUp();

        void onLoginButtonAction(String emailId, String password);

        void onSignUpButtonAction(String name, String email, String password, String confirmPass);

    }

}
