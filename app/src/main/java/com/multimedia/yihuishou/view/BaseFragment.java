package com.multimedia.yihuishou.view;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.multimedia.yihuishou.task.WeakHandler;
import com.multimedia.yihuishou.utils.IActionListener;
import com.multimedia.yihuishou.utils.IInitListener;
import com.multimedia.yihuishou.utils.IUIListener;


/**
 * 基础界面
 *
 * @author DZH 2016年5月21日
 * @description 实现初始化方法，动作方法，刷新机制，切换主题机制（界面类需要继承此类，实现相关方法）
 */
public abstract class BaseFragment
        extends Fragment
        implements IInitListener, IActionListener, IUIListener {

    /**
     * 环境对象
     */
    protected Context mContext;
    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    /**
     * 是否销毁视图
     */
    private boolean isDestroyView;
    /**
     * 当前视图资源ID
     */
    private int mContentResID;
    /**
     * 当前视图
     */
    protected View vContentView;
    /**
     * 是否应用主题
     */
    private boolean isTheme = true;
    /**
     * 标题
     */
    private String mTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();


        mContentResID = setLayoutResId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        mContext = null;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        isDestroyView = false;

        return setContentView(mContentResID);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        vContentView = null;
        isDestroyView = true;
    }

    /**
     * 设置布局资源ID
     *
     * @return
     */
    protected abstract int setLayoutResId();

    /**
     * 设置视图
     *
     * @param layoutResID 布局资源ID
     * @return
     */
    private View setContentView(int layoutResID) {

        if (vContentView == null) {
            isTheme = false;
            vContentView = mInflater.inflate(mContentResID, null);
        }
        initFindViews();
        initViewsValue();
        initViewsEvent();

        return vContentView;
    }

    @Override
    public void initViewsValue() {

    }

    @Override
    public void initViewsEvent() {

    }

    @Override
    public void onUIRefresh(String action, int what, Object obj) {

    }

    @Override
    public void runAction(String action, int what, Object obj) {
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    /**
     * UI对象
     */
    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            onUIRefresh(null, msg.what, msg.obj);
            return true;
        }
    });


    /**
     * 执行UI刷新
     *
     * @param what 标识
     */
    protected void runUIMessage(int what) {
        runUIMessage(what, 0);
    }

    /**
     * 执行UI刷新
     *
     * @param what        标识
     * @param delayMillis 延时（毫秒）
     */
    protected void runUIMessage(int what, long delayMillis) {
        if (mHandler != null) {
            mHandler.removeMessages(what);
            mHandler.sendEmptyMessageDelayed(what, delayMillis);
        }
    }

    /**
     * 执行UI刷新
     *
     * @param what 标识
     * @param obj  对象
     */
    protected void runUIMessage(int what, Object obj) {
        runUIMessage(what, obj, 0);
    }

    /**
     * 执行UI刷新
     *
     * @param what        标识
     * @param obj         对象
     * @param delayMillis 延时（毫秒）
     */
    protected void runUIMessage(int what, Object obj, long delayMillis) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        runUIMessage(msg, delayMillis);
    }

    /**
     * 执行UI刷新
     *
     * @param msg 消息
     */
    protected void runUIMessage(Message msg) {
        runUIMessage(msg, 0);
    }

    /**
     * 执行UI刷新
     *
     * @param msg         消息
     * @param delayMillis 延时（毫秒）
     */
    protected void runUIMessage(Message msg, long delayMillis) {
        if (msg != null && mHandler != null) {
            mHandler.removeMessages(msg.what);
            mHandler.sendMessageDelayed(msg, delayMillis);
        }
    }

    /**
     * 移除UI消息
     *
     * @param what 标识
     */
    protected void removeUIMessages(int what) {
        if (mHandler != null) {
            mHandler.removeMessages(what);
        }
    }

}
