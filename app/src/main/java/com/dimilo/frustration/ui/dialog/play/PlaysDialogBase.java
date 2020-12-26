package com.dimilo.frustration.ui.dialog.play;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.Play;

import static android.text.TextUtils.isEmpty;
import static android.view.WindowManager.LayoutParams.*;
import static com.dimilo.frustration.utils.StringUtils.intToString;
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

    @IdRes
    protected abstract int getFieldToFocus();

    @StringRes
    protected abstract int getMessageResId();

    @StringRes
    protected abstract int getPositiveButtonTextResId();

    protected abstract void showNotValidMessage();

    @StringRes
    protected abstract int getNegativeButtonTextResId();

    @StringRes
    protected abstract int getDialogShowingKeyResId();

    public void show(Play play, PlayCallback playCallback) {
        mPlayCallback = playCallback;
        createAndShowDialog();
        setDefaultValues(play);
        customizeDialog();
        setFocus();
        setPositiveButtonListener();
    }

    public boolean isShowing() {
        return mDialog != null && mDialog.isShowing();
    }

    public Play getPlayState() {
        return _getState();
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

    private void createAndShowDialog() {
        mDialog = new AlertDialog.Builder(mContext)
                .setTitle(R.string.app_name)
                .setMessage(getMessageResId())
                .setView(getLayoutResId())
                .setCancelable(false)
                .setNegativeButton(getNegativeButtonTextResId(), this::onNegativeClick)
                .setPositiveButton(getPositiveButtonTextResId(), null)
                .create();
        mDialog.getWindow().setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
        mDialog.show();
    }

    private int getLayoutResId() {
        return R.layout.dialog_plays;
    }

    private void setDefaultValues(Play play) {
        ((EditText) mDialog.findViewById(R.id.dialog_round)).setText(intToString(play.getRound()));
        ((EditText) mDialog.findViewById(R.id.dialog_player_name)).setText(play.getPlayer());
        ((EditText) mDialog.findViewById(R.id.dialog_player_points)).setText(intToString(play.getPoints()));
    }

    private void customizeDialog() {
        mDialog.findViewById(R.id.dialog_round).setEnabled(isRoundEditable());
        mDialog.findViewById(R.id.dialog_player_name).setEnabled(isPlayerNameEditable());
    }

    private void setFocus() {
        mDialog.findViewById(getFieldToFocus()).requestFocus();
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
        return _getState();
    }

    private Play _getState() {
        return new Play(
                getDialogFieldText(R.id.dialog_player_name),
                stringToInt(getDialogFieldText(R.id.dialog_round)),
                stringToInt(getDialogFieldText(R.id.dialog_player_points)));
    }

    public String getDialogShowingKey() {
        return mContext.getString(getDialogShowingKeyResId());
    }

    String getDialogFieldText(@IdRes int editTextResId) {
        EditText editText = mDialog.findViewById(editTextResId);
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
