package com.dimiaug.frustration.features.userSettings.domain.useCases;

import androidx.lifecycle.LiveData;

import com.dimiaug.frustration.common.domain.exceptions.RepoOperationException;
import com.dimiaug.frustration.common.domain.interfaces.Loggr;
import com.dimiaug.frustration.features.userSettings.domain.interfaces.UserSettingsRepo;
import com.dimiaug.frustration.features.userSettings.models.UserSettings;

public class GetSettingsUseCase {

    private final UserSettingsRepo mUserSettingsRepo;
    private final Loggr mLogger;

    public GetSettingsUseCase(UserSettingsRepo userSettingsRepo, Loggr mLogger) {
        mUserSettingsRepo = userSettingsRepo;
        this.mLogger = mLogger;
    }

    public LiveData<UserSettings> execute() throws RepoOperationException {
        return mUserSettingsRepo.getSettings();

    }
}
