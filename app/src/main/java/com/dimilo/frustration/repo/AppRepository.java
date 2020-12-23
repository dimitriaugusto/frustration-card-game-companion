package com.dimilo.frustration.repo;

import androidx.lifecycle.LiveData;

import com.dimilo.frustration.data.AppDatabase;
import com.dimilo.frustration.data.PlayEntity;

import java.util.List;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository sInstance;

    private final AppDatabase mDatabase;

    private AppRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static AppRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (AppRepository.class) {
                if (sInstance == null) {
                    sInstance = new AppRepository(database);
                }
            }
        }
        return sInstance;
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

    private void executeInWorkerThread(Runnable command) {
        Executors.newSingleThreadExecutor().execute(command);
    }

}
