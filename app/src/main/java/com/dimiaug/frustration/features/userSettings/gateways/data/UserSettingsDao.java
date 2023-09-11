package com.dimiaug.frustration.features.userSettings.gateways.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserSettingsDao {
    @Query("SELECT * FROM userSettings")
    LiveData<List<UserSettingsEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserSettingsEntity... settingsEntities);

    @Update
    void update(UserSettingsEntity UserSettingsEntity);

    @Query("DELETE FROM userSettings")
    void deleteAll();
}
