package com.centura.mcontacts.activity.splash;

import com.centura.mcontacts.DataSource.room.EntityModels.User;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
interface SplashContract {

    interface Model {

        boolean isLoggedIn();

        String getLoggedUser();

        User getUserModel(String userId);

    }

    interface View {

        void startDelay();

        void startLoginActivity();

        void startHomeActivity(User user);
    }

    interface Presenter {

        void onDelayEnded();


    }



}
