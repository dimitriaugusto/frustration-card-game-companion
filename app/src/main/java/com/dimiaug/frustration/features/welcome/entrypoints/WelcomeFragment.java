package com.dimiaug.frustration.features.welcome.entrypoints;

import static org.koin.java.KoinJavaComponent.inject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dimiaug.frustration.R;

public class WelcomeFragment extends Fragment {

    private final WelcomeController mController =
            (WelcomeController) inject(WelcomeController.class).getValue();
    private final WelcomePresenter mPresenter =
            (WelcomePresenter) inject(WelcomePresenter.class).getValue();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return mPresenter.present(inflater, container);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mController.setListeners(view, WelcomeFragment.this);
    }
}