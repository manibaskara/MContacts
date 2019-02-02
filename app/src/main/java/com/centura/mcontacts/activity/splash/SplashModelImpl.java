package com.centura.mcontacts.activity.splash;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.centura.mcontacts.DataSource.DataRepository;
import com.centura.mcontacts.DataSource.room.EntityModels.User;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
public class SplashModelImpl extends AndroidViewModel implements SplashContract.Model {

    private DataRepository dataRepository;

    public SplashModelImpl(@NonNull Application application) {
        super(application);
            this.dataRepository = new DataRepository(application);
    }

    @Override
    public boolean isLoggedIn() {
        return dataRepository.isLoggedIn();
    }

    @Override
    public String getLoggedUser() {
        return dataRepository.getLoggedUser();
    }

    public User getUserModel(String userId){
        return dataRepository.getUser(userId);
    }
}
