package com.multimedia.yihuishou.view.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.multimedia.yihuishou.R;




public class OkCancelDialog extends UIBase {

    private TextView vTitle;
    private View     vHorizontalLine;
    private TextView vInfo;
    private TextView vOk;
    private View     vVerticalLine;
    private TextView vCancel;

    public OkCancelDialog(Context context) {
        super(context);
    }

    public OkCancelDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OkCancelDialog(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void initFindViews() {
        inflateView(R.layout.okcancel_dialog);

        vTitle = findViewById(R.id.v_title);
        vHorizontalLine = findViewById(R.id.v_horizontal_line);
        vInfo = findViewById(R.id.v_info);
        vOk = findViewById(R.id.v_ok);
        vVerticalLine = findViewById(R.id.v_vertical_line);
        vCancel = findViewById(R.id.v_cancel);
    }

    /**
     * 设置视图
     *
     * @param titleRes       标题资源
     * @param infoRes        内容资源
     * @param okRes          确定资源
     * @param cancelRes      取消资源
     * @param okListener     确定监听器
     * @param cancelListener 取消监听器
     */
    public void setViews(int titleRes, int infoRes, int okRes, int cancelRes, View.OnClickListener okListener,
                         View.OnClickListener cancelListener) {
        setViews(getContext().getString(titleRes), getContext().getString(infoRes), okRes, cancelRes, okListener,
                cancelListener);
    }

    /**
     * 设置视图
     *
     * @param title          标题
     * @param info           内容
     * @param okRes          确定资源
     * @param cancelRes      取消资源
     * @param okListener     确定监听器
     * @param cancelListener 取消监听器
     */
    public void setViews(String title, String info, int okRes, int cancelRes, View.OnClickListener okListener,
                         View.OnClickListener cancelListener) {
        if (!TextUtils.isEmpty(title)) {
            vTitle.setVisibility(VISIBLE);
            vTitle.setText(title);
        } else {
            vTitle.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(info)) {
            vInfo.setVisibility(VISIBLE);
            vInfo.setText(info);
        } else {
            vInfo.setVisibility(GONE);
        }
        if (okRes > 0) {
            vOk.setVisibility(VISIBLE);
            vOk.setText(okRes);
        } else {
            vOk.setVisibility(GONE);
        }
        if (cancelRes > 0) {
            vCancel.setVisibility(VISIBLE);
            vCancel.setText(cancelRes);
        } else {
            vCancel.setVisibility(GONE);
        }
        if (okRes > 0 && cancelRes > 0) {
            vVerticalLine.setVisibility(VISIBLE);
        } else {
            vVerticalLine.setVisibility(GONE);
        }
        vOk.setOnClickListener(okListener);
        vCancel.setOnClickListener(cancelListener);
    }

}
