package com.dimiaug.frustration.common.gateways.settings.repo;

import com.dimiaug.frustration.common.domain.interfaces.SettingsRepo;
import com.dimiaug.frustration.common.gateways.settings.data.SettingsDao;

public class SettingsRepoImpl implements SettingsRepo {
    private final SettingsDao mSettingsDao;

    public SettingsRepoImpl(SettingsDao settingsDao) {
        mSettingsDao = settingsDao;
    }

    @Override
    public boolean isThereSettings() {
        return false;
    }
}
