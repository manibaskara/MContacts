package com.centura.mcontacts.DataSource.room.EntityModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
@Entity(tableName = "user_contacts",
        foreignKeys = {@ForeignKey(entity = User.class,
                parentColumns = "mailId"
                , childColumns = "ownerEmailId"
                , onDelete = CASCADE)
        })
public class UserContact {

    @PrimaryKey(autoGenerate = true)
    private int _id;
    private String contactName;
    private String ownerEmailId;
    private String phoneNumber;

    public UserContact(@NonNull String ownerEmailId, @NonNull String contactName, String phoneNumber) {
        this.ownerEmailId = ownerEmailId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getOwnerEmailId() {
        return ownerEmailId;
    }

    public void setOwnerEmailId(@NonNull String ownerEmailId) {
        this.ownerEmailId = ownerEmailId;
    }

    @NonNull
    public String getContactName() {
        return contactName;
    }

    public void setContactName(@NonNull String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
