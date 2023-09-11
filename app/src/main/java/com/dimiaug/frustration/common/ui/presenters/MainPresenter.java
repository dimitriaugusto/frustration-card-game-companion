package com.dimiaug.frustration.common.ui.presenters;

import android.view.Menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;

import com.dimiaug.frustration.R;
import com.dimiaug.frustration.common.domain.interfaces.Loggr;
import com.dimiaug.frustration.features.userSettings.models.UserSettings;

public class MainPresenter {
    private final AppCompatActivity mActivity;
    private final Loggr mLogger;

    public MainPresenter(AppCompatActivity activity, Loggr mLogger) {
        mActivity = activity;
        this.mLogger = mLogger;
    }

    public void presentWelcomeScreen() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mActivity.setContentView(R.layout.activity_main);

        Toolbar toolbar = mActivity.findViewById(R.id.toolbar);
        mActivity.setSupportActionBar(toolbar);
    }

    public boolean inflateOptionsMenu(Menu menu) {
        mActivity.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showSettingsDialog(LiveData<UserSettings> userSettings) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity)
                .setTitle("R.string.settings")
                .setMessage("LOADING")
                .setPositiveButton("R.string.ok", null)
                .show();

        userSettings.observe(mActivity, (settings) -> {
            dialog.setMessage(settings.toString());
        });
    }

    public void showNoSettingsDialog() {
        new AlertDialog.Builder(mActivity)
                .setTitle("R.string.settings")
                .setMessage("COULD NOT LOAD SETTINGS")
                .setPositiveButton("R.string.ok", null)
                .show();
    }

    public void showAboutDialog() {
        new AlertDialog.Builder(mActivity)
                .setTitle("R.string.about")
                .setMessage("IMPLEMENT ABOUT SCREEN")
                .setPositiveButton("R.string.ok", null)
                .show();

    }
}
