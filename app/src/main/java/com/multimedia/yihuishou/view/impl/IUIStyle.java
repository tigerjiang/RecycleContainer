package com.multimedia.yihuishou.view.impl;

/**
 * UI风格样式接口
 * Created by DZH on 16-12-28.
 */
public interface IUIStyle {

    /** 风格样式——0：深色 */
    public static final int STYLE_DARK = 0;
    /** 风格样式——1：浅色 */
    public static final int STYLE_LIGHT = 1;

    public void setStyle(int style);

    public int getStyle();

    public void onStyleDark();

    public void onStyleLight();

    public void onStyleChange(int style);
}
