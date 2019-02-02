package com.centura.mcontacts.DataSource;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.centura.mcontacts.DataSource.room.Dao.UserContactsDao;
import com.centura.mcontacts.DataSource.room.Dao.UserDAO;
import com.centura.mcontacts.DataSource.room.EntityModels.User;
import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;
import com.centura.mcontacts.DataSource.room.UserDataBase;
import com.centura.mcontacts.DataSource.sharedPrefs.SharedPreference;

import java.util.List;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
public class DataRepository {

    private UserDAO userDAO;
    private UserContactsDao userContactsDao;
    private SharedPreference sharedPreference;

    public DataRepository(Application application) {
        UserDataBase db = UserDataBase.getUserDataBaseInstance(application.getApplicationContext());
        userDAO = db.getUserDao();
        userContactsDao = db.getUserContactsDao();
        sharedPreference = new SharedPreference(application);
    }

    public void insertUser(User user) {
        userDAO.insertUser(user);
    }


    public User getUser(String emailId) {
        return userDAO.getUser(emailId);
    }


    public LiveData<List<UserContact>> getUserContacts(String emailId) {
        return userContactsDao.getContactsForUser(emailId);
    }

    public void insertUserContact(UserContact userContact) {
        userContactsDao.insertContact(userContact);
    }

    public void updateUserContact(UserContact userContact) {
        userContactsDao.updateContact(userContact);
    }

    public void deleteUserContact(int _id) {
        userContactsDao.deleteContactById(_id);
    }

    public boolean isLoggedIn(){
        return sharedPreference.isLoggedIn();
    }

    public String getLoggedUser() {
        return sharedPreference.getLoggedUser();
    }
    public void saveLoggedUser(boolean isLoggedIn, String loggedUserEmail) {
       sharedPreference.saveLoggedUser(isLoggedIn, loggedUserEmail);
    }

}
