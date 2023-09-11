package com.dimiaug.frustration.common.ui.presenters;

import android.view.Menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.dimiaug.frustration.R;

public class MainPresenter {
    private final AppCompatActivity mActivity;

    public MainPresenter(AppCompatActivity activity) {
        mActivity = activity;
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

    public void showYesSettingsDialog() {
        new AlertDialog.Builder(mActivity)
                .setTitle("R.string.settings")
                .setMessage("TO BE IMPLEMENTED")
                .setPositiveButton("R.string.ok", null)
                .show();
    }

    public void showNoSettingsDialog() {
        new AlertDialog.Builder(mActivity)
                .setTitle("R.string.settings")
                .setMessage("NO SETTING AVAILABLE")
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
