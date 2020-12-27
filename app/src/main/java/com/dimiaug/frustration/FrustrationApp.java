package com.dimiaug.frustration;

import android.app.Application;

import com.dimiaug.frustration.data.AppDatabase;
import com.dimiaug.frustration.repo.AppRepository;

public class FrustrationApp extends Application {

    public static final String TAG = "Frustration";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppRepository getRepository() {
        return AppRepository.getInstance(getDatabase());
    }

    private AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

}
