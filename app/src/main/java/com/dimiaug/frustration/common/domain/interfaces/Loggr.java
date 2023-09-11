package com.dimiaug.frustration.common.domain.interfaces;

public interface Loggr {
    void e(String message, Exception e);

    void d(String observingUserSettings);
}
