package com.multimedia.yihuishou.view.impl;

/**
 * UI显示或隐藏接口：实现此接口的卡片将不会走通用的卡片曝光逻辑
 * @author DZH 2016年12月08日
 */
public interface IUIShowOrHideListener {
    /**
     * UI显示
     */
    public void onUIShow();

    /**
     * UI隐藏
     */
    public void onUIHide();
}
