package com.example.user.keepsolid.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.user.keepsolid.R;

/**
 * Created by Mexanik on 30.09.2018.
 */

public class BaseActitvity {

    public static Dialog getDialogProgress(@NonNull Context context) {
        if (context == null) {
            return null;
        }
        Dialog dialogProgressBar = new Dialog(context);
        dialogProgressBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogProgressBar.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogProgressBar.setContentView(R.layout.progress_view);
        dialogProgressBar.setCancelable(false);
        return dialogProgressBar;
    }

}
