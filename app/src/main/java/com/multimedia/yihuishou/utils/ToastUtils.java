package com.multimedia.yihuishou.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.yihuishou.R;


public class ToastUtils {

    public static final int TOAST = 0;
    public static final int TIPS  = 1;

    /**
     * 类型——TOAST
     */
    private static final int TYPE_TOAST    = 0;
    /**
     * 类型——TIPS
     */
    private static final int TYPE_TIPS     = 1;
    /**
     * 时间——3秒
     */
    private static final int DURATION_3000 = 3000;
    /**
     * 时间——2秒
     */
    private static final int DURATION_2000 = 2000;

    private static ToastUtils mInstance;

    private Toast mToast;
    private int   mType;

    private        View     vLayout;
    private        TextView vTxtTitle;
    private        long     mShowTime;
    private static Context  mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static ToastUtils getInstance() {
        if (mInstance == null) {
            synchronized (ToastUtils.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化TOAST视图
     *
     * @param context
     * @param type
     */
    private void initToastView(Context context, int type) {
        mType = type;
        switch (mType) {
            case TYPE_TOAST:
                int layoutId = R.layout.galleryplus_ui_toast;
                vLayout = LayoutInflater.from(context).inflate(layoutId, null);
                vTxtTitle = (TextView) vLayout.findViewById(R.id.v_toast);
                break;
            default:
                break;
        }
        mToast = new Toast(context);
        mToast.setView(vLayout);
        mShowTime = 0;
    }

    public void keepShowTime() {
        if (mInstance != null) {
            mInstance.mShowTime = System.currentTimeMillis();
        }
    }

    /**
     * 显示
     */
    public void show() {
        if (mToast != null) mToast.show();
    }

    /**
     * 关闭
     */
    public void dismiss() {
        if (mInstance != null && mInstance.mToast != null) {
            if (mInstance.mShowTime == 0 || (mInstance.mToast != null && mInstance.mToast.getDuration() < System
                    .currentTimeMillis() - mInstance.mShowTime)) {
                mInstance.mToast.cancel();
                mInstance.mToast = null;
            }
        }
    }

    public ToastUtils showToast(int resId) {
        int dimenId = R.dimen.dp_67_3;

        return showToast(mContext.getString(resId), DURATION_3000, Gravity
                .BOTTOM, 0, mContext.getResources().getDimensionPixelSize(dimenId));
    }

    public ToastUtils showToast(String text) {
        int dimenId = R.dimen.dp_67_3;

        return showToast(text, DURATION_3000, Gravity.BOTTOM, 0, mContext
                .getResources().getDimensionPixelSize(dimenId));
    }

    public ToastUtils showToast(int resId, int yOffset) {
        return showToast(mContext.getString(resId), DURATION_3000, Gravity
                .BOTTOM, 0, 0);
    }

    public ToastUtils showToast(String text, int yOffset) {
        return showToast(text, DURATION_3000, Gravity.BOTTOM, 0, yOffset);
    }

    public ToastUtils showToastCenter(String text) {
        return showToast(text, DURATION_3000, Gravity.CENTER, 0, 0);
    }

    public ToastUtils showToast(String text, int duration, int gravity, int xOffset, int yOffset) {
        if (TextUtils.isEmpty(text)) return null;
        dismiss();
        initToastView(mContext, TYPE_TOAST);
        vTxtTitle.setText(text);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.setDuration(duration);
        mToast.show();
        return mInstance;
    }
}
