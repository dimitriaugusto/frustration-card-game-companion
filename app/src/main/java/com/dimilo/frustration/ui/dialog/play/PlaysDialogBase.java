package com.dimilo.frustration.ui.dialog.play;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.Play;

import static android.text.TextUtils.isEmpty;
import static com.dimilo.frustration.utils.StringUtils.posIntToString;
import static com.dimilo.frustration.utils.StringUtils.stringToInt;

public abstract class PlaysDialogBase {

    final Context mContext;
    private AlertDialog mDialog;
    PlayCallback mPlayCallback;

    public PlaysDialogBase(Context context) {
        mContext = context;
    }

    protected abstract boolean isPlayerNameEditable();

    protected abstract boolean isRoundEditable();

    @StringRes
    protected abstract int getMessageResId();

    @StringRes
    protected abstract int getPositiveButtonTextResId();

    protected abstract void showNotValidMessage();

    @StringRes
    protected abstract int getNegativeButtonTextResId();

    public void show(Play play, PlayCallback playCallback) {
        mPlayCallback = playCallback;
        mDialog = createAndShowDialog();
        setDefaultValues(play);
        customizeDialog();
        setPositiveButtonListener();
    }

    private AlertDialog createAndShowDialog() {
        return new AlertDialog.Builder(mContext)
                .setTitle(R.string.app_name)
                .setMessage(getMessageResId())
                .setView(getLayoutResId())
                .setCancelable(false)
                .setNegativeButton(getNegativeButtonTextResId(), this::onNegativeClick)
                .setPositiveButton(getPositiveButtonTextResId(), null)
                .show();
    }

    protected int getLayoutResId() {
        return R.layout.dialog_plays;
    }

    private void setDefaultValues(Play play) {
        ((EditText) mDialog.findViewById(R.id.dialog_round)).setText(posIntToString(play.getRound()));
        ((EditText) mDialog.findViewById(R.id.dialog_player_name)).setText(play.getPlayer());
    }

    private void customizeDialog() {
        mDialog.findViewById(R.id.dialog_round).setEnabled(isRoundEditable());
        mDialog.findViewById(R.id.dialog_player_name).setEnabled(isPlayerNameEditable());
    }

    private void onNegativeClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        mPlayCallback.call(new Play());
    }

    private void setPositiveButtonListener() {
        Button button = mDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setOnClickListener(new DialogListener(mDialog) {
            @Override
            public void onClick(View view) {
                onPositiveClick(dialog);
            }
        });
    }

    private void onPositiveClick(Dialog dialog) {
        if (!isPositiveClickValid()) {
            showNotValidMessage();
            return; // no dismissal = dialog keeps open
        }
        mPlayCallback.call(onValidPositiveClick());
        dialog.dismiss();
    }

    protected boolean isPositiveClickValid() {
        String round = getDialogFieldText(R.id.dialog_round);
        String playerName = getDialogFieldText(R.id.dialog_player_name);
        String playerPoints = getDialogFieldText(R.id.dialog_player_points);
        return !isEmpty(round) && !isEmpty(playerName) && !isEmpty(playerPoints);
    }

    protected Play onValidPositiveClick() {
        return new Play(
                getDialogFieldText(R.id.dialog_player_name),
                stringToInt(getDialogFieldText(R.id.dialog_round)),
                stringToInt(getDialogFieldText(R.id.dialog_player_points)));
    }

    String getDialogFieldText(@IdRes int editTextResId) {
        EditText editText = ((AlertDialog) mDialog).findViewById(editTextResId);
        return editText.getText().toString();
    }

    abstract static class DialogListener implements View.OnClickListener {
        final Dialog dialog;

        public DialogListener(Dialog dialog) {
            this.dialog = dialog;
        }
    }

    public interface PlayCallback {
        void call(Play play);
    }

}
