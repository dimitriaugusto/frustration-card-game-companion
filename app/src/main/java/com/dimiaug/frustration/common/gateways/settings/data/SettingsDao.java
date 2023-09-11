package com.dimiaug.frustration.common.gateways.settings.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDao {
    @Query("SELECT * FROM settings")
    LiveData<List<SettingsEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SettingsEntity... settingsEntities);

    @Update
    void update(SettingsEntity SettingsEntity);

    @Query("DELETE FROM settings")
    void deleteAll();
}
