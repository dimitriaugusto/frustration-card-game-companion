package com.dimiaug.frustration.common.gateways.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dimiaug.frustration.features.play.gateways.data.PlayDao;
import com.dimiaug.frustration.features.play.gateways.data.PlayEntity;
import com.dimiaug.frustration.common.gateways.settings.data.SettingsDao;
import com.dimiaug.frustration.common.gateways.settings.data.SettingsEntity;

@Database(
        entities = {
                PlayEntity.class,
                SettingsEntity.class
        },
        version = 2
)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "frustration_game.db";

    public abstract PlayDao playDao();

    public abstract SettingsDao settingsDao();

}
