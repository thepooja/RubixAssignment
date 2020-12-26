package com.rubix.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BrandEntity.class,MobileEntity.class},exportSchema =  false,version = 1)
public abstract  class MyDatabase extends RoomDatabase {

    private static final String DB_NAME ="mobile.db";

    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getDatabaseInstance(Context context)
    {
        if(databaseInstance == null)
        {

            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,DB_NAME)
                    .build();

        }

        return databaseInstance;
    }

    public abstract BrandDao dao();

}
