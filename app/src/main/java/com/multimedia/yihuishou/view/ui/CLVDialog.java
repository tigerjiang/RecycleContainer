package com.multimedia.yihuishou.view.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.multimedia.yihuishou.utils.DialogUtils;


public class CLVDialog extends DialogUtils {

    public static final String KEY_SHOWMIUIOKCANCEL       = "showOkCancel";

    public static void showOkCancel(Context context, String title, String info, int leftRes, int rightRes,
                                        View.OnClickListener leftListener, View.OnClickListener rightListener,
                                        boolean closeable) {
        OkCancelDialog ui = new OkCancelDialog(context);
        ui.setViews(title, info, leftRes, rightRes, leftListener, rightListener);
        showDialog(context, initBottomDialog(context, ui, closeable), KEY_SHOWMIUIOKCANCEL);
    }
}
