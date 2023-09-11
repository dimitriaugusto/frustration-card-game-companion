package com.dimiaug.frustration.common.gateways.log;

import android.util.Log;

import com.dimiaug.frustration.common.domain.interfaces.Loggr;

public class LoggrImpl implements Loggr {

    private static final String TAG = "FrustrationApp";

    @Override
    public void e(String message, Exception e) {
        Log.e(TAG, getCallerClass() + ": " + message, e);
    }

    private static String getCallerClass() {
        return Thread.currentThread().getStackTrace()[4].getClassName();
    }

    @Override
    public void d(String message) {
        Log.e(TAG, getCallerClass() + ": " + message);
    }
}
