package com.dimiaug.frustration.common.gateways.repo;

import java.util.concurrent.Executors;

public class BaseRepository {
    protected void executeInWorkerThread(Runnable command) {
        Executors.newSingleThreadExecutor().execute(command);
    }
}
