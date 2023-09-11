package com.dimiaug.frustration.common.gateways.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsEntity;
import com.dimiaug.frustration.features.play.gateways.data.PlayDao;
import com.dimiaug.frustration.features.play.gateways.data.PlayEntity;
import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsDao;

@Database(
        entities = {
                PlayEntity.class,
                UserSettingsEntity.class
        },
        version = 3
)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "frustration_game.db";

    public abstract PlayDao playDao();

    public abstract UserSettingsDao settingsDao();

}
