package com.centura.mcontacts.activity.homeActivity;

import android.os.Handler;

import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
public class HomePresenterImpl implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private HomeContract.Model homeViewModel;

    HomePresenterImpl(HomeContract.View homeView, HomeContract.Model homeViewModel) {
        this.homeView = homeView;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void OnFabClick() {
        homeView.showNewContact();
    }

    @Override
    public void deleteContact(UserContact userContact) {
        homeViewModel.deleteUserContact(userContact.get_id());
        homeView.hideBottomSheet();
        homeView.hideKeyBoard();
        homeView.showToast("Contact deleted.");
    }

    @Override
    public void onSave(String ownerEmail, String name, String number) {
        if (name.isEmpty()) {
            homeView.setNameError("Enter contact name");
        } else if (number.isEmpty()) {
            homeView.setNumberError("Enter contact number.");
        } else {

            homeViewModel.insertUserContact(new UserContact(ownerEmail, name, number));
            homeView.hideBottomSheet();
            homeView.hideKeyBoard();
            homeView.showToast("Contact saved successfully.");
        }
    }

    @Override
    public void onEnableEditClick() {
        homeView.enableEditing();
        homeView.showKeyBoard();
    }

    @Override
    public void onEdit(UserContact userContact, String name, String number) {

        String strOldName = userContact.getContactName();
        String strOldNumber = userContact.getPhoneNumber();
        if (name.isEmpty()) {
            homeView.setNameError("Enter contact name");
        } else if (number.isEmpty()) {
            homeView.setNumberError("Enter contact number.");
        } else if (strOldName.equals(name) && strOldNumber.equals(number)) {
            homeView.setNameError("No changes detected.");
        } else {
            userContact.setContactName(name);
            userContact.setPhoneNumber(number);
            homeViewModel.updateUserContact(userContact);
            homeView.hideBottomSheet();
            homeView.hideKeyBoard();
            homeView.showToast("Contact updated successfully.");
        }


    }

    @Override
    public void onContactSelected(UserContact contact) {
        homeView.hideBottomSheet();
        new Handler().postDelayed(() -> {
            homeView.showEditContact(contact);
            homeView.disableEditing();
        }, 100);
    }

    @Override
    public void onLogoutClick() {
        homeViewModel.onLogoutClick();
        homeView.showToast("Logged out successfully.");
        homeView.logOut();
    }
}