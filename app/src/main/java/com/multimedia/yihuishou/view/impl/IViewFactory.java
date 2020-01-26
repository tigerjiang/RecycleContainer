package com.multimedia.yihuishou.view.impl;

import android.view.View;

/**
 * View工厂接口
 * Created by DZH on 17-4-25.
 * 实现不同View界面时，需要重写此接口方法
 */
public interface IViewFactory {

    /**
     * 建立View
     * @param type 类型
     * @return
     */
    public View createView(int type);
}
