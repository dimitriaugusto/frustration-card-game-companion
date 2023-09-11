package com.dimiaug.frustration.features.welcome.entrypoints;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimiaug.frustration.R;

public class WelcomePresenter {
    View present(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ((TextView) view.findViewById(R.id.welcome_text)).setText(R.string.welcome_text);

        return view;
    }
}
