package com.multimedia.yihuishou.view.impl;

/**
 * UI显示或隐藏接口:卡片自身额外控件单独实现的监听，即使实现此监听，任然会走通用的曝光打点逻辑
 *
 * @author ZhouZhenWu 2019年09月24日
 */
public interface IUIShowOrHideSelfExtraListener {
    /**
     * UI显示
     */
    public void onUIShow();

    /**
     * UI隐藏
     */
    public void onUIHide();
}
