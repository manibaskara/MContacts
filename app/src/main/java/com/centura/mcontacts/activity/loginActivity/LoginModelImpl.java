package com.centura.mcontacts.activity.loginActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.centura.mcontacts.DataSource.DataRepository;
import com.centura.mcontacts.DataSource.room.EntityModels.User;


/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
public class LoginModelImpl extends AndroidViewModel implements LoginContract.Model {

    private DataRepository dataRepository;

    public LoginModelImpl(Application application) {
        super(application);
        this.dataRepository = new DataRepository(application);
    }

    @Override
    public User getUser(String emailId) {
        return dataRepository.getUser(emailId);
    }

    @Override
    public void storeLoggedIn(boolean isLoggedIn, String mailId) {
        dataRepository.saveLoggedUser(isLoggedIn, mailId);
    }

    @Override
    public void insertUser(User user) {
        dataRepository.insertUser(user);
    }
}