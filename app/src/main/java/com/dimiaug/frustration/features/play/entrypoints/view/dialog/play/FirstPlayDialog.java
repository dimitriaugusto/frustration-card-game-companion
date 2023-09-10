package com.dimiaug.frustration.features.play.entrypoints.view.dialog.play;

import android.content.Context;
import android.widget.Toast;

import com.dimiaug.frustration.R;

public class FirstPlayDialog extends PlaysDialogBase {

    public FirstPlayDialog(Context context) {
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
    protected int getFieldToFocus() {
        return R.id.dialog_player_name;
    }

    @Override
    protected int getMessageResId() {
        return R.string.message_first_play;
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
        return R.string.button_no_more_players;
    }

    @Override
    protected int getDialogShowingKeyResId() {
        return R.string.key_first_play_dialog_showing;
    }

}
