package com.ldy.flexframe.base.loading;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ldy.flexframe.base.R;

/**
 * Created by ldy on 2016/12/12.
 */

public class LoadingDialog {

    private AlertDialog alertDialog;
    private final Context context;
    private View contentView;
    private TextView tvText;

    public LoadingDialog(Context context) {
        this.context = context;

    }

    public void show() {
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(LayoutInflater.from(context).inflate(R.layout.dialog_circle_progress, null));
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
        }
        alertDialog.show();
    }

    public void show(String text) {
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            contentView = LayoutInflater.from(context).inflate(R.layout.dialog_circle_progress, null);
            builder.setView(contentView);
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
        }
        if (text != null) {
            if (tvText == null) {
                tvText = ((TextView) contentView.findViewById(R.id.dialog_message));
            }
            tvText.setText(text);
        }
        alertDialog.show();
    }

    public void dismiss() {
        if (alertDialog != null)
            alertDialog.dismiss();
    }
}
