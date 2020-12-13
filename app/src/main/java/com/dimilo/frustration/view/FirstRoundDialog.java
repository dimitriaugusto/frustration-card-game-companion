package com.dimilo.frustration.view;

import android.content.Context;
import android.widget.Toast;

import com.dimilo.frustration.R;

public class FirstRoundDialog extends PlaysDialogBase {

    public FirstRoundDialog(Context context) {
        super(context);
    }

    @Override
    protected boolean isPlayerNameEditable() {
        return true;
    }

    @Override
    protected boolean isRoundEditable() {
        return false;
    }

    @Override
    protected int getMessageResId() {
        return R.string.message_first_round;
    }

    @Override
    protected int getPositiveButtonTextResId() {
        return R.string.button_add_player_and_points;
    }

    @Override
    protected void showNotValidMessage() {
        Toast.makeText(mContext, R.string.dialog_player_or_points_empty, Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getNegativeButtonTextResId() {
        return R.string.button_quit_adding_players;
    }

}
