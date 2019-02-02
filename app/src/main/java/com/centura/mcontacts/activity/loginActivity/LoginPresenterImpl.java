package com.centura.mcontacts.activity.loginActivity;

import android.util.Patterns;

import com.centura.mcontacts.DataSource.room.EntityModels.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
public class LoginPresenterImpl implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private LoginModelImpl loginModelImpl;

    LoginPresenterImpl(LoginContract.View loginView, LoginModelImpl loginModelImpl) {
        this.loginView = loginView;
        this.loginModelImpl = loginModelImpl;
        loginView.onClicks();
    }

    @Override
    public void onSignUp() {
        loginView.showSignUpDialog();
    }

    @Override
    public void onLoginButtonAction(String emailId, String password) {
        if (emailId.isEmpty()) {
            loginView.setEmailError("Enter your Email id");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            loginView.setEmailError("Enter a valid Email id");
        } else if (password.isEmpty()) {
            loginView.setPasswordError("Enter your password");
        } else if (isInvalidPassword(password)) {
            loginView.setPasswordError("Password pattern invalid");
        } else if (loginModelImpl.getUser(emailId) == null) {
            loginView.setEmailError("User Id doesn't exist. Please Sign Up.");
        } else if (!loginModelImpl.getUser(emailId).getPassword().equals(password)) {
            loginView.setPasswordError("Incorrect password. Please try again.");
        } else {
            User user = loginModelImpl.getUser(emailId);
            loginModelImpl.storeLoggedIn(true, user.getMailId());
            loginView.onSignInSuccess(user);
        }
    }

    @Override
    public void onSignUpButtonAction(String name, String email, String password, String confirmPass) {

        if (name.isEmpty()) {
            loginView.showSignUpNameError("Please Enter your Name.");
        } else if (email.isEmpty()) {
            loginView.showSignUpEmailError("Please Enter your Email.");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginView.showSignUpEmailError("Please Enter a valid Email.");
        } else if (loginModelImpl.getUser(email) != null) {
            loginView.showSignUpEmailError("Email already Exists.");
        } else if (password.isEmpty()) {
            loginView.showSignUpPasswordError("Please Enter new Password.");
        } else if (isInvalidPassword(password)) {
            loginView.showSignUpPasswordError("Password pattern invalid.");
        } else if (confirmPass.isEmpty()) {
            loginView.showSignUpConfirmPassError("Please confirm your password here.");
        } else if (!password.equals(confirmPass)) {
            loginView.showSignUpConfirmPassError("Passwords doesn't match");
        } else {
            loginModelImpl.insertUser(new User(email, password, name));
            loginView.onSignUpSuccess();
            loginView.onSignInSuccess(loginModelImpl.getUser(email));
        }
    }

    private boolean isInvalidPassword(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
        Matcher matcher = Pattern.compile(pattern).matcher(password);
        return !matcher.matches();
    }
}
