package com.centura.mcontacts.activity.splash;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
public class SplashPresenterImpl implements SplashContract.Presenter {

    private SplashContract.View splashView;
    private SplashContract.Model splashModel;

    SplashPresenterImpl(SplashContract.View splashView, SplashContract.Model splashModel) {
        this.splashView = splashView;
        this.splashModel = splashModel;
        splashView.startDelay();
    }

    @Override
    public void onDelayEnded() {
        if (splashModel.isLoggedIn()) {
            String userMailId = splashModel.getLoggedUser();
            splashView.startHomeActivity(splashModel.getUserModel(userMailId));

        } else {
            splashView.startLoginActivity();
        }

    }
}
