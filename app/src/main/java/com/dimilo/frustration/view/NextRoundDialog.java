package com.dimilo.frustration.view;

import android.content.Context;
import android.widget.Toast;

import com.dimilo.frustration.R;

public class NextRoundDialog extends PlaysDialogBase {

    public NextRoundDialog(Context context) {
        super(context);
    }

    @Override
    protected boolean isPlayerNameEditable() {
        return false;
    }

    @Override
    protected boolean isRoundEditable() {
        return false;
    }

    @Override
    protected int getMessageResId() {
        return R.string.message_next_round;
    }

    @Override
    protected int getPositiveButtonTextResId() {
        return R.string.button_add_points;
    }

    @Override
    protected void showNotValidMessage() {
        Toast.makeText(mContext, R.string.dialog_points_empty, Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getNegativeButtonTextResId() {
        return 0;
    }

}
