package com.dimiaug.frustration.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.dimiaug.frustration.R;

public class ResetGameDialog {

    private final Context mContext;
    private AlertDialog mDialog;
    BooleanDialogCallback mCallback;

    public ResetGameDialog(Context context) {
        mContext = context;
    }

    public void show(BooleanDialogCallback callback) {
        mCallback = callback;
        mDialog = createAndShowDialog();
    }

    public boolean isShowing() {
        return mDialog != null && mDialog.isShowing();
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

    public String getDialogShowingKey() {
        return mContext.getString(R.string.key_reset_game_dialog_showing);
    }

    private AlertDialog createAndShowDialog() {
        return new AlertDialog.Builder(mContext)
                .setTitle(R.string.app_name)
                .setMessage(R.string.message_confirm_reset_game)
                .setNegativeButton(android.R.string.no, this::onNegativeClick)
                .setPositiveButton(android.R.string.yes, this::onPositiveClick)
                .show();
    }

    private void onNegativeClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    private void onPositiveClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        mCallback.onResetConfirmed();
    }


    public interface BooleanDialogCallback {
        void onResetConfirmed();
    }

}
