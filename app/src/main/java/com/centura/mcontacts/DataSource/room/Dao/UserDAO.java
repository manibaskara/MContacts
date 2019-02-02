package com.centura.mcontacts.DataSource.room.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.centura.mcontacts.DataSource.room.EntityModels.User;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE mailId=:emailId")
    User getUser(String emailId);
/*

    @Delete
    void deleteUser(User user);
*/

}