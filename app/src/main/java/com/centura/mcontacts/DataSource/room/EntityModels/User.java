package com.centura.mcontacts.DataSource.room.EntityModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
@Entity(tableName = "users")
public class User implements Serializable {


    @PrimaryKey
    @NonNull
    private String mailId;
    private String password;
    private String userName;

    public User(@NonNull String mailId, String password, String userName) {
        this.mailId = mailId;
        this.password = password;
        this.userName = userName;
    }

    @NonNull
    public String getMailId() {
        return mailId;
    }

    public void setMailId(@NonNull String mailId) {
        this.mailId = mailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
