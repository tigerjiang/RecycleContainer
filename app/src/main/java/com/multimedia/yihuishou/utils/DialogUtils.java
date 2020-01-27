package com.multimedia.yihuishou.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;


import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.log.LogUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 对话框工具类
 *
 * @author DZH 2017年1月9日
 * @description
 */
public class DialogUtils {

    private static final String TAG = "DialogUtils";

    public static final String KEY_SHOWGRANTPERMISSIONDIALOG = "showGrantPermissionDialog";

    /**
     * 对话框列表
     */
    private static SparseArray<HashMap<String, Dialog>> mArray = new SparseArray<HashMap<String, Dialog>>();

    /**
     * 显示对话框
     *
     * @param context 环境对象
     * @param dialog  对话框
     * @return
     */
    protected static boolean showDialog(Context context, Dialog dialog, String key) {
        if (context == null || dialog == null) return false;
        final int code = context.hashCode();
        HashMap<String, Dialog> map = mArray.get(code);
        if (map == null) map = new HashMap<String, Dialog>();
        dismiss(map.get(key));
        map.put(key, dialog);
        mArray.put(code, map);
        dialog.show();
        return true;
    }

    public static Dialog getDialog(Context context, String key) {
        return mArray.get(context.hashCode()).get(key);
    }

    /**
     * 关闭对话框
     *
     * @param dialog 对话框
     */
    protected static void dismiss(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                if (dialog.getContext() instanceof ContextWrapper
                        && ((ContextWrapper) dialog.getContext()).getBaseContext() instanceof Activity) {
                    final Activity activity = (Activity) ((ContextWrapper) dialog.getContext()).getBaseContext();
                    if (SDKUtils.equalAPI_17_JELLY_BEAN_MR1()) {
                        if (!activity.isFinishing() && !activity.isDestroyed()) {
                            dialog.dismiss();
                        }
                    } else if (!activity.isFinishing()) {
                        dialog.dismiss();
                    }
                } else {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e);
        } finally {
            dialog = null;
        }
    }

    /**
     * 关闭对话框
     *
     * @param context 环境对象
     * @return
     */
    public static boolean dismiss(Context context, String key) {
        if (context == null) return false;
        final int code = context.hashCode();
        HashMap<String, Dialog> map = mArray.get(code);
        if (map == null) return false;
        dismiss(map.remove(key));
        return true;
    }

    /**
     * 关闭对话框
     *
     * @param context 环境对象
     * @return
     */
    public static boolean dismiss(Context context) {
        if (context == null) return false;
        final int code = context.hashCode();
        HashMap<String, Dialog> map = mArray.get(code);
        if (map == null) return false;
        Iterator<Map.Entry<String, Dialog>> list = map.entrySet().iterator();
        while (list.hasNext()) {
            dismiss(list.next().getValue());
        }
        map.clear();
        mArray.remove(code);
        return true;
    }

    public static void dismissAll() {
        for (int i = 0, c = mArray.size(); i < c; i++) {
            HashMap<String, Dialog> map = mArray.valueAt(i);
            if (map == null) continue;
            Iterator<Map.Entry<String, Dialog>> list = map.entrySet().iterator();
            while (list.hasNext()) {
                dismiss(list.next().getValue());
            }
            map.clear();
        }
        mArray.clear();
    }

    /**
     * 初始化一个新的对话框（中间）
     *
     * @param context   环境对象
     * @param ui        视图
     * @param closeable 是否可以关闭
     * @return
     */
    protected static Dialog initMiddleDialog(Context context, View ui, boolean closeable) {
        return initDialog(context, ui, closeable, Gravity.CENTER_VERTICAL | Gravity.FILL_HORIZONTAL);
    }

    /**
     * 初始化一个新的对话框（底部）
     *
     * @param context   环境对象
     * @param ui        视图
     * @param closeable 是否可以关闭
     * @return
     */
    protected static Dialog initBottomDialog(Context context, View ui, boolean closeable) {
        return initDialog(context, ui, closeable, Gravity.BOTTOM | Gravity.FILL_HORIZONTAL);
    }

    /**
     * 初始化一个新的对话框
     *
     * @param context   环境对象
     * @param ui        视图
     * @param closeable 是否可以关闭
     * @return
     */
    protected static Dialog initDialog(Context context, View ui, boolean closeable) {
        return initDialog(context, ui, closeable, null);
    }

    /**
     * 初始化一个新的对话框
     *
     * @param context         环境对象
     * @param ui              视图
     * @param closeable       是否可以关闭
     * @param dismissListener Dialog的关闭监听
     * @return
     */
    protected static Dialog initDialog(Context context, View ui, boolean closeable,
                                       DialogInterface.OnDismissListener dismissListener) {
        Dialog dialog = new Dialog(context, R.style.ui_dialog);
        dialog.setContentView(ui);
        if (closeable) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnKeyListener(null);
        } else {
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(eventOnKey);
        }
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setWindowAnimations(R.style.ui_dialog);
        dialog.setOnDismissListener(dismissListener);
        return dialog;
    }

    /**
     * 初始化一个新的对话框
     *
     * @param context           环境对象
     * @param ui                视图
     * @param closeable         是否可以关闭
     * @param onDismissListener Dialog的关闭监听
     * @param onCancelListener  Dialog的外部点击关闭监听
     * @param onShowListener    Dialog的显示监听
     * @return
     */
    protected static Dialog initDialog(Context context, View ui, boolean closeable,
                                       DialogInterface.OnDismissListener onDismissListener,
                                       DialogInterface.OnCancelListener onCancelListener,
                                       DialogInterface.OnShowListener onShowListener) {
        Dialog dialog = new Dialog(context, R.style.ui_dialog);
        dialog.setContentView(ui);
        if (closeable) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnKeyListener(null);
        } else {
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(eventOnKey);
        }
        WindowManager.LayoutParams windowParams = dialog.getWindow().getAttributes();
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.ui_dialog);
        dialog.setOnDismissListener(onDismissListener);
        dialog.setOnCancelListener(onCancelListener);
        dialog.setOnShowListener(onShowListener);
        return dialog;
    }

    /**
     * 初始化一个无动画效果的对话框
     *
     * @param context           环境对象
     * @param ui                视图
     * @param closeable         是否可以关闭
     * @param onDismissListener Dialog的关闭监听
     * @param onCancelListener  Dialog的外部点击关闭监听
     * @param onShowListener    Dialog的显示监听
     * @return
     */
    protected static Dialog initDialogNoAnim(Context context, View ui, boolean closeable,
                                             DialogInterface.OnDismissListener onDismissListener,
                                             DialogInterface.OnCancelListener onCancelListener,
                                             DialogInterface.OnShowListener onShowListener) {
        Dialog dialog = new Dialog(context, R.style.ui_dialog);
        dialog.setContentView(ui);
        if (closeable) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnKeyListener(null);
        } else {
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(eventOnKey);
        }
        WindowManager.LayoutParams windowParams = dialog.getWindow().getAttributes();
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setOnDismissListener(onDismissListener);
        dialog.setOnCancelListener(onCancelListener);
        dialog.setOnShowListener(onShowListener);
        return dialog;
    }

    /**
     * 初始化一个新的对话框
     *
     * @param context   环境对象
     * @param ui        视图
     * @param closeable 是否可以关闭
     * @param gravity   位置
     * @return
     */
    protected static Dialog initDialog(Context context, View ui, boolean closeable, int gravity) {
        Dialog dialog = new Dialog(context, R.style.ui_dialog);
        dialog.setContentView(ui);
        if (closeable) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnKeyListener(null);
        } else {
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(eventOnKey);
        }
        WindowManager.LayoutParams windowParams = dialog.getWindow().getAttributes();
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(gravity);
        dialog.getWindow().setWindowAnimations(R.style.ui_dialog);
        return dialog;
    }

    protected static void setEditDialog(Dialog dialog) {
        if (dialog == null) return;
        dialog.getWindow()
                .clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    /**
     * 按键事件
     */
    protected static DialogInterface.OnKeyListener eventOnKey = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (KeyEvent.KEYCODE_BACK == keyCode) {
                return true;
            }
            return false;
        }
    };
    

}
