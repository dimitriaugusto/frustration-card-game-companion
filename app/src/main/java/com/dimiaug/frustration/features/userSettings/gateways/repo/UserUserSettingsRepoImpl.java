package com.dimiaug.frustration.features.userSettings.gateways.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.dimiaug.frustration.common.domain.exceptions.RepoOperationException;
import com.dimiaug.frustration.common.domain.interfaces.Loggr;
import com.dimiaug.frustration.common.gateways.repo.BaseRepository;
import com.dimiaug.frustration.features.userSettings.domain.interfaces.UserSettingsRepo;
import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsDao;
import com.dimiaug.frustration.features.userSettings.gateways.data.UserSettingsEntity;
import com.dimiaug.frustration.features.userSettings.domain.models.UserSettings;

import java.util.List;

public class UserUserSettingsRepoImpl extends BaseRepository implements UserSettingsRepo {
    private final UserSettingsDao mUserSettingsDao;
    private final Loggr mLoggr;

    public UserUserSettingsRepoImpl(UserSettingsDao settingsDao, Loggr mLoggr) {
        mUserSettingsDao = settingsDao;
        this.mLoggr = mLoggr;
    }

    @Override
    public LiveData<UserSettings> getSettings() throws RepoOperationException {
        try {
            executeInWorkerThread(() -> {
                UserSettingsEntity seti = new UserSettingsEntity("boardFontSize", 1, null, 12, null);
                mUserSettingsDao.insertAll(seti);
            });

            LiveData<List<UserSettingsEntity>> entities = mUserSettingsDao.getAll();
            return Transformations.map(entities, UserSettings::toModel);
        } catch (Exception e) {
            mLoggr.e("getSettings failed in DAO", e);
            throw new RepoOperationException();
        }
    }
}
