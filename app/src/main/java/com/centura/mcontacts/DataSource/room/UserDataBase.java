package com.centura.mcontacts.DataSource.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.centura.mcontacts.DataSource.room.Dao.UserContactsDao;
import com.centura.mcontacts.DataSource.room.Dao.UserDAO;
import com.centura.mcontacts.DataSource.room.EntityModels.User;
import com.centura.mcontacts.DataSource.room.EntityModels.UserContact;

/**
 * Created by Manikandan Baskaran on 01-02-2019.
 */
@Database(entities = {User.class, UserContact.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    private static volatile UserDataBase INSTANCE;

    public abstract UserDAO getUserDao();

    public abstract UserContactsDao getUserContactsDao();

    public static UserDataBase getUserDataBaseInstance(Context mContext) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(mContext.getApplicationContext()
                    , UserDataBase.class
                    , "user_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
