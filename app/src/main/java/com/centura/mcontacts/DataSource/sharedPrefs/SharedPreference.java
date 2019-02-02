package com.centura.mcontacts.DataSource.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Manikandan Baskaran on 02-02-2019.
 */
public class SharedPreference implements PrefContract{

    private static final String PREF_IS_LOGGED_IN = "is_logged_in";
    private static final String PREF_LOGGED_USER = "logged_user";
    private static final String PREF_NAME = "shared_pref_name";

    private SharedPreferences sharedPreference;

    public SharedPreference(Context context){

        sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn(){
        return sharedPreference.getBoolean(PREF_IS_LOGGED_IN, false);
    }

    public String getLoggedUser() {
        return sharedPreference.getString(PREF_LOGGED_USER, null);
    }
    public void saveLoggedUser(boolean isLoggedIn, String loggedUserEmail) {
        sharedPreference.edit().putBoolean(PREF_IS_LOGGED_IN,isLoggedIn).apply();
        sharedPreference.edit().putString(PREF_LOGGED_USER, loggedUserEmail).apply();
    }
}