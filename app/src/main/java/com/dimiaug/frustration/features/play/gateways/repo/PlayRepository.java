package com.dimiaug.frustration.features.play.gateways.repo;

import androidx.lifecycle.LiveData;

import com.dimiaug.frustration.common.gateways.data.AppDatabase;
import com.dimiaug.frustration.common.gateways.repo.BaseRepository;
import com.dimiaug.frustration.features.play.gateways.data.PlayEntity;

import java.util.List;

public class PlayRepository extends BaseRepository {

    private final AppDatabase mDatabase;

    public PlayRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public LiveData<List<PlayEntity>> getPlays() {
        return mDatabase.playDao().getAll();
    }

    public void insertPlay(PlayEntity playEntity) {
        executeInWorkerThread(() -> mDatabase.playDao().insertAll(playEntity));
    }

    public void updatePlay(PlayEntity playEntity) {
        executeInWorkerThread(() -> mDatabase.playDao().update(playEntity));
    }

    public void deleteAllPlays() {
        executeInWorkerThread(() -> mDatabase.playDao().deleteAll());
    }

}
