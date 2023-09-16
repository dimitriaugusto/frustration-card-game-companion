package com.dimiaug.frustration.features.userSettings.domain.models;

import androidx.annotation.NonNull;

import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsEntity;

import java.util.List;

public class UserSettings {
    private Integer boardFontSize;

    public Integer getBoardFontSize() {
        return boardFontSize;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserSettings {\n" +
                "boardFontSize=" + boardFontSize +
                "\n}";
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
}
