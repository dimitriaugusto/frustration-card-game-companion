package com.dimiaug.frustration.common.entrypoints;

import static org.koin.java.KoinJavaComponent.inject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.dimiaug.frustration.common.ui.controllers.OptionsMenuController;
import com.dimiaug.frustration.common.domain.interfaces.CommonInjection;
import com.dimiaug.frustration.common.ui.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private final CommonInjection mCommonInjection =
            (CommonInjection) inject(CommonInjection.class).getValue();
    private final MainPresenter mPresenter =
            mCommonInjection.getMainPresenter(MainActivity.this);
    private final OptionsMenuController mController =
            mCommonInjection.getMainController(MainActivity.this, mPresenter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.presentWelcomeScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return mPresenter.inflateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mController.setListeners(item)) return true;
        return super.onOptionsItemSelected(item);
    }

}