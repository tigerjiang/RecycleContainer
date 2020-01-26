package com.multimedia.yihuishou.view.impl;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * 单击事件监听器接口
 * Created by DZH on 17-1-6.
 */
public interface IUIClickListener {

    /**
     * 设置单击事件监听器
     * @param clickListener 监听器
     */
    public void setUIClickListener(OnClickListener clickListener);

    /**
     * 设置长按事件监听器
     * @param longClickListener 监听器
     */
    public void setUILongClickListener(View.OnLongClickListener longClickListener);
}
