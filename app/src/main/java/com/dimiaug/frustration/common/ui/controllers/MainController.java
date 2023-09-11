package com.dimiaug.frustration.common.ui.controllers;

import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dimiaug.frustration.R;
import com.dimiaug.frustration.common.domain.IsThereSettingsUseCase;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;

public class MainController {
    private final AppCompatActivity mActivity;
    private final IsThereSettingsUseCase mIsThereSettingsUseCase;
    private final MainPresenter mPresenter;

    public MainController(AppCompatActivity activity, MainPresenter presenter,
                          IsThereSettingsUseCase isThereSettingsUseCase) {
        mActivity = activity;
        mPresenter = presenter;
        mIsThereSettingsUseCase = isThereSettingsUseCase;
    }

    public boolean setListeners(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                if (mIsThereSettingsUseCase.execute()) {
                    mPresenter.showYesSettingsDialog();
                } else {
                    mPresenter.showNoSettingsDialog();
                }
                return true;
            case R.id.action_about:
                mPresenter.showAboutDialog();
        }
        return false;
    }
}
