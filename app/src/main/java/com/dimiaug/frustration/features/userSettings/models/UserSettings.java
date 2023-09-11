package com.dimiaug.frustration.features.userSettings.models;

import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsEntity;

import java.util.List;

public class UserSettings {
    private Integer boardFontSize;

    public Integer getBoardFontSize() {
        return boardFontSize;
    }

    /* static methods - keep apart */
    public static UserSettings toModel(List<UserSettingsEntity> entityList) {
        UserSettings model = new UserSettings();
        entityList.forEach((entity -> setModelSetting(model, entity)));
        return model;
    }

    private static void setModelSetting(UserSettings model, UserSettingsEntity entity) {
        switch (entity.setting) {
            case "boardFontSize" -> model.boardFontSize = entity.intValue;
        }
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "boardFontSize=" + boardFontSize +
                '}';
    }
}
