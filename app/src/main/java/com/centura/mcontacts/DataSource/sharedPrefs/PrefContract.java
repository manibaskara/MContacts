package com.centura.mcontacts.DataSource.sharedPrefs;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
interface PrefContract {
    boolean isLoggedIn();

    String getLoggedUser();

    void saveLoggedUser(boolean isLoggedIn, String loggedUserEmail);
}
