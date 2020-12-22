package com.dimilo.frustration.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayDao {

    @Query("SELECT * FROM play")
    LiveData<List<PlayEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PlayEntity... playEntities);

    @Update
    void update(PlayEntity playEntity);

    @Query("DELETE FROM play")
    void deleteAll();

}
