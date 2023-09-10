package com.dimiaug.frustration.common.gateways.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PlayEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "frustration_game.db";

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

    public abstract PlayDao playDao();

}
