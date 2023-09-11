package com.dimiaug.frustration.features.userSettings.gateways.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "userSettings", primaryKeys = {"setting"})
public class UserSettingsEntity {
    @NonNull
    public String setting;
    @NonNull
    public Integer type; // 0 - boolean, 1 - int, 2 - String
    public Boolean booleanValue;
    public Integer intValue;
    public String stringValue;

    public UserSettingsEntity(@NonNull String setting, @NonNull Integer type, Boolean booleanValue,
                              Integer intValue, String stringValue) {
        this.setting = setting;
        this.type = type;
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
    }
}
