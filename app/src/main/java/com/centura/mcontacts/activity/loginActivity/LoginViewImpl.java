package com.centura.mcontacts.activity.loginActivity;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.centura.mcontacts.DataSource.room.EntityModels.User;
import com.centura.mcontacts.R;
import com.centura.mcontacts.activity.homeActivity.HomeViewImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */

public class LoginViewImpl extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    private Context context;

    private Dialog signUpDialog;

    private EditText etSignUpName,
            etSignUpEmail,
            etSignUpPassword,
            etSignUpConfirmPassword;


    private LoginPresenterImpl loginPresenter;
    public static final String USER_CLASS = "user_class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        context = LoginViewImpl.this;
        ButterKnife.bind(this);
        LoginModelImpl loginModelImpl = ViewModelProviders.of(this).get(LoginModelImpl.class);
        loginPresenter = new LoginPresenterImpl(this, loginModelImpl);
    }

    @Override
    public void onClicks() {
        btnLogin.setOnClickListener(view -> {
            if (etEmail.getText() != null && etPassword.getText() != null) {
                String strEmail = etEmail.getText().toString().trim();
                String strPass = etPassword.getText().toString().trim();
                loginPresenter.onLoginButtonAction(strEmail, strPass);
            }
        });
        tvSignUp.setOnClickListener(view -> loginPresenter.onSignUp());
    }

    @Override
    public void setEmailError(String message) {
        etEmail.requestFocus();
        etEmail.setError(message);
    }

    @Override
    public void setPasswordError(String message) {

        etPassword.requestFocus();
        etPassword.setError(message);
    }

    @Override
    public void onSignInSuccess(User user) {
        Intent intent = new Intent(this, HomeViewImpl.class);
        intent.putExtra(USER_CLASS, user);
        startActivity(intent);
        finish();
    }

    @Override
    public void showSignUpDialog() {

        etEmail.setText("");
        etPassword.setText("");
        etEmail.setError(null);
        etPassword.setError(null);

        signUpDialog = new Dialog(context);
        signUpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (inflater != null) {
            view = inflater.inflate(R.layout.signup_dialog_view, null, false);

            etSignUpName = view.findViewById(R.id.etName);
            etSignUpEmail = view.findViewById(R.id.etEmail);
            etSignUpPassword = view.findViewById(R.id.etPassword);
            etSignUpConfirmPassword = view.findViewById(R.id.etConfirmPassword);
            Button btnSignUp = view.findViewById(R.id.btn_signUp);

            btnSignUp.setOnClickListener(view1 -> loginPresenter.onSignUpButtonAction(
                    etSignUpName.getText().toString().trim()
                    , etSignUpEmail.getText().toString().trim()
                    , etSignUpPassword.getText().toString().trim()
                    , etSignUpConfirmPassword.getText().toString().trim()
            ));

            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            signUpDialog.setContentView(view);
            final Window window = signUpDialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                signUpDialog.show();
            }
        }
    }

    @Override
    public void showSignUpEmailError(String message) {
        if (etSignUpEmail != null) {
            etSignUpEmail.requestFocus();
            etSignUpEmail.setError(message);
        }
    }

    @Override
    public void showSignUpNameError(String message) {
        if (etSignUpName != null) {
            etSignUpName.requestFocus();
            etSignUpName.setError(message);
        }
    }

    @Override
    public void showSignUpPasswordError(String message) {
        if (etSignUpPassword != null) {
            etSignUpPassword.requestFocus();
            etSignUpPassword.setError(message);
        }
    }

    @Override
    public void showSignUpConfirmPassError(String message) {
        if (etSignUpConfirmPassword != null) {
            etSignUpConfirmPassword.requestFocus();
            etSignUpConfirmPassword.setError(message);
        }
    }

    @Override
    public void onSignUpSuccess() {
        if (signUpDialog != null && signUpDialog.isShowing()) {
            signUpDialog.dismiss();
        }
        Toast.makeText(context, "Sign Up Success.", Toast.LENGTH_SHORT).show();
    }
}