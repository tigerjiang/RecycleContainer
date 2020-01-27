package com.multimedia.yihuishou.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import com.multimedia.yihuishou.view.impl.IActivityListener;
import com.multimedia.yihuishou.view.impl.IInitListener;
import com.multimedia.yihuishou.view.impl.IUIClickListener;

/**
 * UI基础类
 *
 * @author DZH 2015年11月8日
 * @description
 */
public abstract class UIBase
        extends RelativeLayout
        implements IInitListener, IActivityListener, IUIClickListener, Checkable {

    /**
     * 基础视图
     */
    protected View            vView;
    /**
     * 单击事件监听器
     */
    protected OnClickListener mUIClickListener;
    /**
     * 风格样式
     */
    private   int             mStyle;
    /**
     * 是否选中
     */
    private   boolean         isChecked;
    /**
     * 额外信息
     */
    private   String          mExtras;

    public UIBase(Context context) {
        this(context, null, 0);
    }

    public UIBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initFindViews();
        initViewsValue();
        initViewsEvent();
    }

    @Override
    public void setUILongClickListener(OnLongClickListener longClickListener) {

    }

    /**
     * 加载布局
     *
     * @param layoutResId
     */
    public void inflateView(int layoutResId) {
        vView = inflate(getContext(), layoutResId, this);
    }

    @Override
    public void initViewsValue() {

    }

    @Override
    public void initViewsEvent() {

    }

    @Override
    public void runAction(String action, int what, Object obj) {

    }

    @Override
    public void onUIRefresh(String action, int what, Object obj) {

    }


    @Override
    public void setUIClickListener(OnClickListener clickListener) {
        mUIClickListener = clickListener;
    }



    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {

    }

    public String getExtras() {
        return mExtras;
    }

    public void setExtras(String extras) {
        mExtras = extras;
    }
}
