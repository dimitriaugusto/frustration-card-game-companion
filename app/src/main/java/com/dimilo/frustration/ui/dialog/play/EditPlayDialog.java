package com.dimilo.frustration.ui.dialog.play;

import android.content.Context;
import android.widget.Toast;

import com.dimilo.frustration.R;

public class EditPlayDialog extends PlaysDialogBase {

    public EditPlayDialog(Context context) {
        super(context);
    }

    @Override
    protected boolean isPlayerNameEditable() {
        return true;
    }

    @Override
    protected boolean isRoundEditable() {
        return true;
    }

    @Override
    protected int getMessageResId() {
        return R.string.message_edit_play;
    }

    @Override
    protected int getPositiveButtonTextResId() {
        return R.string.button_save_points;
    }

    @Override
    protected void showNotValidMessage() {
        Toast.makeText(mContext, R.string.dialog_round_or_player_or_points_empty, Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getNegativeButtonTextResId() {
        return android.R.string.cancel;
    }

    @Override
    protected int getDialogShowingKeyResId() {
        return R.string.key_edit_play_dialog_showing;
    }

}
