package com.dimiaug.frustration.common.ui.controllers;

import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.dimiaug.frustration.R;
import com.dimiaug.frustration.features.userSettings.domain.useCases.GetSettingsUseCase;
import com.dimiaug.frustration.common.domain.exceptions.RepoOperationException;
import com.dimiaug.frustration.common.domain.interfaces.Loggr;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;

public class OptionsMenuController {
    private final AppCompatActivity mActivity;
    private final GetSettingsUseCase mGetSettingsUseCase;
    private final MainPresenter mPresenter;
    private final Loggr mLoggr;

    public OptionsMenuController(AppCompatActivity activity, MainPresenter presenter,
                                 GetSettingsUseCase getSettingsUseCase, Loggr mLoggr) {
        mActivity = activity;
        mPresenter = presenter;
        mGetSettingsUseCase = getSettingsUseCase;
        this.mLoggr = mLoggr;
    }

    public boolean setListeners(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings -> {
                getUserSettingsUseCase();
                return true;
            }
            case R.id.action_about -> {
                mPresenter.showAboutDialog();
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private void getUserSettingsUseCase() {
        try {
            mPresenter.showSettingsDialog(mGetSettingsUseCase.execute());
        } catch (RepoOperationException e) {
            mLoggr.e("Error getting settings from repo", e);
            mPresenter.showNoSettingsDialog();
        }

    }
}
