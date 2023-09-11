package com.dimiaug.frustration.features.welcome.ui.presenters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimiaug.frustration.R;

public class WelcomePresenter {
    private final LayoutInflater mInflater;
    private final ViewGroup mContainer;

    public WelcomePresenter(LayoutInflater inflater, ViewGroup container) {
        mInflater = inflater;
        mContainer = container;
    }

    public View present() {
        View view = mInflater.inflate(R.layout.fragment_welcome, mContainer, false);
        ((TextView) view.findViewById(R.id.welcome_text)).setText(R.string.welcome_text);
        return view;
    }
}
