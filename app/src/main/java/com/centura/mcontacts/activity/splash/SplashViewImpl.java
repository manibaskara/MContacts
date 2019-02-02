package com.centura.mcontacts.activity.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.centura.mcontacts.DataSource.room.EntityModels.User;
import com.centura.mcontacts.R;
import com.centura.mcontacts.activity.homeActivity.HomeViewImpl;
import com.centura.mcontacts.activity.loginActivity.LoginViewImpl;

import static com.centura.mcontacts.activity.loginActivity.LoginViewImpl.USER_CLASS;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
public class SplashViewImpl extends AppCompatActivity implements SplashContract.View {


    private SplashPresenterImpl splashPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashModelImpl loginViewModel = ViewModelProviders.of(this).get(SplashModelImpl.class);
        splashPresenterImpl = new SplashPresenterImpl(this, loginViewModel);
    }


    @Override
    public void startDelay() {
        new Handler().postDelayed(() -> splashPresenterImpl.onDelayEnded(), 500);
    }

    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(SplashViewImpl.this, LoginViewImpl.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startHomeActivity(User user) {
        Intent intent = new Intent(SplashViewImpl.this, HomeViewImpl.class);
        intent.putExtra(USER_CLASS, user);
        startActivity(intent);
        finish();

    }
}