package com.dimilo.frustration.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.dimilo.frustration.R;
import com.dimilo.frustration.model.Play;

public class ResetGameDialog {

    final Context mContext;
    private AlertDialog mDialog;
    BooleanDialogCallback mCallback;

    public ResetGameDialog(Context context) {
        mContext = context;
    }

    public void show(BooleanDialogCallback callback) {
        mCallback = callback;
        mDialog = createAndShowDialog();
    }

    private AlertDialog createAndShowDialog() {
        return new AlertDialog.Builder(mContext)
                .setTitle(R.string.app_name)
                .setMessage(R.string.message_confirm_reset_game)
                .setNegativeButton(android.R.string.yes, this::onNegativeClick)
                .setPositiveButton(android.R.string.no, this::onPositiveClick)
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
