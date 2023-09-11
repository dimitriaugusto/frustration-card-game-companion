package com.dimiaug.frustration.common.gateways.settings.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "settings", primaryKeys = {"setting"})
public class SettingsEntity {
    @NonNull
    public String setting;
    @NonNull
    public Integer type; // 0 - boolean, 1 - int, 2 - String
    public Boolean booleanValue;
    public Integer intValue;
    public String stringValue;

    public SettingsEntity(@NonNull String setting, @NonNull Integer type, Boolean booleanValue,
                          Integer intValue, String stringValue) {
        this.setting = setting;
        this.type = type;
        this.booleanValue = booleanValue;
        this.intValue = intValue;
        this.stringValue = stringValue;
    }
}
