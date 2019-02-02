package com.centura.mcontacts.DataSource.room.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;

import java.util.List;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
@Dao
public interface UserContactsDao {

    @Insert
    void insertContact(UserContact userContact);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrUpdateContact(UserContact userContact);

    @Update
    void updateContact(UserContact userContact);

    @Delete
    void deleteContact(UserContact userContact);

    @Query("DELETE FROM user_contacts WHERE _id = :id")
    void deleteContactById(int id);

    @Query("SELECT * FROM user_contacts ORDER BY _id ASC")
    LiveData<List<UserContact>> getAllContacts();

    @Query("SELECT * FROM user_contacts WHERE ownerEmailId=:emailId")
    LiveData<List<UserContact>> getContactsForUser(final String emailId);
}
