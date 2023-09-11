package com.dimiaug.frustration.common.domain;

import com.dimiaug.frustration.common.domain.interfaces.SettingsRepo;

public class IsThereSettingsUseCase {

    private final SettingsRepo mSettingsRepo;

    public IsThereSettingsUseCase(SettingsRepo settingsRepo) {
        mSettingsRepo = settingsRepo;
    }

    public boolean execute() {
        return mSettingsRepo.isThereSettings();
    }
}
