package com.multimedia.yihuishou.view.impl;

/**
 * UI刷新接口
 * @author DZH 2015年10月12日
 * @description 用于UI刷新时的回调
 */
public interface IUIListener {

	/** 设置视图值 */
	public static final String ACTION_SET_VALUE = "ACTION_SET_VALUE";

	/**
	 * 当UI刷新
	 * @param action 动作标识
	 * @param what 动作标识（用于Handler刷新）
	 * @param obj 对象
	 */
	public void onUIRefresh(String action, int what, Object obj);
}
