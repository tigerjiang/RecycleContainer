package com.multimedia.yihuishou.view.impl;

/**
 * 动作接口
 * @author DZH 2015年10月12日
 * @description 用于界面实现操作功能
 */
public interface IActionListener {

	/**
	 * 执行动作
	 * @param action 动作标识
	 * @param what 动作标识（用于Handler刷新）
	 * @param obj 对象
	 */
	public void runAction(String action, int what, Object obj);
}
