package com.dimiaug.frustration.features.play.entrypoints.view.dialog.play;

import android.content.Context;
import android.widget.Toast;

import com.dimiaug.frustration.R;

public class NextPlayDialog extends PlaysDialogBase {

    public NextPlayDialog(Context context) {
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
    protected int getFieldToFocus() {
        return R.id.dialog_player_points;
    }

    @Override
    protected int getMessageResId() {
        return R.string.message_next_play;
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
        return android.R.string.cancel;
    }

    @Override
    protected int getDialogShowingKeyResId() {
        return R.string.key_next_play_dialog_showing;
    }

}
