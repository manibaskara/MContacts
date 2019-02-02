package com.centura.mcontacts.activity.homeActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.centura.mcontacts.DataSource.DataRepository;
import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;

import java.util.List;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
public class HomeModelImpl extends AndroidViewModel implements HomeContract.Model {
    private DataRepository dataRepository;

    public HomeModelImpl(@NonNull Application application) {
        super(application);
        this.dataRepository = new DataRepository(application);
    }


    @Override
    public LiveData<List<UserContact>> getUserContacts(String ownerEmail) {
        return dataRepository.getUserContacts(ownerEmail);
    }

    @Override
    public void insertUserContact(UserContact userContact) {
        dataRepository.insertUserContact(userContact);
    }

    @Override
    public void deleteUserContact(int id) {
        dataRepository.deleteUserContact(id);
    }

    @Override
    public void updateUserContact(UserContact userContact) {
        dataRepository.updateUserContact(userContact);
    }

    @Override
    public void onLogoutClick(){
        dataRepository.saveLoggedUser(false, null);
    }
}
