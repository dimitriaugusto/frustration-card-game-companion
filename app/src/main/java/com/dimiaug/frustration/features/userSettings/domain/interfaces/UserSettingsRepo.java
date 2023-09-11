package com.dimiaug.frustration.features.userSettings.domain.interfaces;

import androidx.lifecycle.LiveData;

import com.dimiaug.frustration.common.domain.exceptions.RepoOperationException;
import com.dimiaug.frustration.features.userSettings.models.UserSettings;

public interface UserSettingsRepo {

    LiveData<UserSettings> getSettings() throws RepoOperationException;
}
