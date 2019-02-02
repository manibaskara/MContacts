package com.centura.mcontacts.activity.homeActivity;

import android.arch.lifecycle.LiveData;

import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;

import java.util.List;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
public interface HomeContract {

    interface Model {

        LiveData<List<UserContact>> getUserContacts(String ownerEmail);

        void insertUserContact(UserContact userContact);

        void deleteUserContact(int id);

        void updateUserContact(UserContact userContact);

        void onLogoutClick();

    }

    interface View {

        void showNewContact();

        void showEditContact(UserContact user);

        void hideBottomSheet();

        void init();

        void onClicks();

        void setNameError(String message);

        void setNumberError(String message);

        void showToast(String message);

        void hideKeyBoard();

        void showKeyBoard();

        void disableEditing();

        void enableEditing();
        void logOut();

    }

    interface Presenter {

        void OnFabClick();

        void deleteContact(UserContact userContact);

        void onSave(String ownerEmail, String name, String number);

        void onEnableEditClick();

        void onEdit(UserContact userContact, String name, String number);

        void onContactSelected(UserContact contact);

        void onLogoutClick();


    }

}