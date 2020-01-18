package com.multimedia.yihuishou.log;

import android.text.TextUtils;
import android.util.Log;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class LogUtils {

    /**
     * 网络相关日志
     */
    private static final String NETWORK_EVENT = "network_event";
    /**
     * 追踪统计日志
     */
    private static final String TRACKER_EVENT = "tracker_event";
    /**
     * 可关闭的业务日志
     */
    private static final String CLOSED_FUNCTION_EVENT = "closed_function_event";

    /**
     * 1：普通级别
     */
    private static final int LOG_V = 1;
    /**
     * 2：调试级别
     */
    private static final int LOG_D = 2;
    /**
     * 3：消息级别
     */
    private static final int LOG_I = 3;
    /**
     * 4：警告级别
     */
    private static final int LOG_W = 4;
    /**
     * 5：异常级别
     */
    private static final int LOG_E = 5;

    private static final String THROWABLE_IS_NULL = "Throwable is null";

    /**
     * LOG信息标识
     */
    public static final String TAG = "yihuishou";
    /**
     * 日志级别自定义队列
     */
    private static Map<String, Boolean> mTagMap;
    private static List<String> mLogList;

    /**
     * 添加自定义日志标识
     *
     * @param logTag 日志标识
     * @return
     */
    public static boolean addLogTag(String logTag) {
        if (!checkLogTag(logTag)) {
            mTagMap.put(logTag, true);
            return true;
        }
        return false;
    }

    /**
     * 清除自定义日志标识
     */
    public static void clearLogTag() {
        if (mTagMap != null) mTagMap.clear();
    }

    /**
     * 检查自定义日志标识
     *
     * @param logTag 日志标识
     * @return
     */
    private static boolean checkLogTag(String logTag) {
        if (mTagMap == null) {
            mTagMap = new HashMap<String, Boolean>();
        }
        return mTagMap.containsKey(logTag);
    }

    /**
     * 获取日志标识
     *
     * @param tag 标识对象
     * @return
     */
    private static String getLogTag(Object tag) {
        if (tag == null || tag instanceof String) {
            return (String) tag;
        } else {
            return tag.getClass().getSimpleName();
        }
    }

    /**
     * 执行日志输出
     *
     * @param logLevel 日志级别
     * @param tag      标识对象
     * @param event    事件
     * @param msg      信息
     */
    private static void runShowLog(int logLevel, Object tag, String event, Object msg) {

        String logTag = TextUtils.isEmpty(getLogTag(tag))? TAG:getLogTag(tag);
        StringBuffer logMsg = new StringBuffer("[ ").append(event).append(" ] ").append(msg);
        switch (logLevel) {
            case LOG_D:
                Log.d(logTag, logMsg.toString());
                break;
            case LOG_I:
                Log.i(logTag, logMsg.toString());
                break;
            case LOG_W:
                Log.w(logTag, logMsg.toString());
                break;
            case LOG_E:
                Log.e(logTag, logMsg.toString());
                break;
            default:
                Log.v(logTag, logMsg.toString());
                break;
        }

    }


    /**
     * 获取异常信息
     *
     * @param e 异常对象
     * @return
     */
    public static String getErrorInfo(Throwable e) {
        if (e == null) return THROWABLE_IS_NULL;
        Writer w = new StringWriter();
        PrintWriter pw = new PrintWriter(w);
        e.printStackTrace(pw);
        pw.close();
        return w.toString();
    }


    /**
     * 输出信息——1：普通级别
     *
     * @param msg 信息
     */
    public static void v(String msg) {
        runShowLog(LOG_V, TAG, "", msg);
    }

    /**
     * 输出信息——2：调试级别
     *
     * @param msg 信息
     */
    public static void d(String msg) {
        runShowLog(LOG_D, TAG, "", msg);
    }

    /**
     * 输出信息——3：消息级别
     *
     * @param msg 信息
     */
    public static void i(String msg) {
        runShowLog(LOG_I, TAG, "", msg);
    }

    /**
     * 输出信息——4：警告级别
     *
     * @param msg 信息
     */
    public static void w(String msg) {
        runShowLog(LOG_W, TAG, "", msg);
    }

    /**
     * 输出信息——5：异常级别
     *
     * @param msg 信息
     */
    public static void e(String msg) {
        runShowLog(LOG_E, TAG, "", msg);
    }

    /**
     * 输出信息——1：普通级别
     *
     * @param event 事件标识
     * @param msg   信息
     */
    public static void v(String event, Object msg) {
        runShowLog(LOG_V, TAG, event, msg);
    }

    /**
     * 输出信息——2：调试级别
     *
     * @param event 事件标识
     * @param msg   信息
     */
    public static void d(String event, Object msg) {
        runShowLog(LOG_D, TAG, event, msg);
    }

    /**
     * 输出信息——3：消息级别
     *
     * @param event 事件标识
     * @param msg   信息
     */
    public static void i(String event, Object msg) {
        runShowLog(LOG_I, TAG, event, msg);
    }

    /**
     * 输出信息——4：警告级别
     *
     * @param event 事件标识
     * @param msg   信息
     */
    public static void w(String event, Object msg) {
        runShowLog(LOG_W, TAG, event, msg);
    }

    /**
     * 输出信息——5：异常级别
     *
     * @param event 事件标识
     * @param msg   信息
     */
    public static void e(String event, Object msg) {
        runShowLog(LOG_E, TAG, event, msg);
    }

    /**
     * 输出信息——1：普通级别
     *
     * @param tag   标识
     * @param event 事件标识
     * @param msg   信息
     */
    public static void v(Object tag, String event, Object msg) {
        runShowLog(LOG_V, tag, event, msg);
    }

    /**
     * 输出信息——2：调试级别
     *
     * @param tag   标识
     * @param event 事件标识
     * @param msg   信息
     */
    public static void d(Object tag, String event, Object msg) {
        runShowLog(LOG_D, tag, event, msg);
    }

    /**
     * 输出信息——3：消息级别
     *
     * @param tag   标识
     * @param event 事件标识
     * @param msg   信息
     */
    public static void i(Object tag, String event, Object msg) {
        runShowLog(LOG_I, tag, event, msg);
    }

    /**
     * 输出信息——4：警告级别
     *
     * @param tag   标识
     * @param event 事件标识
     * @param msg   信息
     */
    public static void w(Object tag, String event, Object msg) {
        runShowLog(LOG_W, tag, event, msg);
    }

    /**
     * 输出信息——5：异常级别
     *
     * @param tag   标识
     * @param event 事件标识
     * @param msg   信息
     */
    public static void e(Object tag, String event, Object msg) {
        runShowLog(LOG_E, tag, event, msg);
    }

    /**
     * 输出统计日志
     *
     * @param tag 标识
     * @param msg 信息
     */
    public static void trackerLog(Object tag, String msg) {
        runShowLog(LOG_D, tag, TRACKER_EVENT, msg);
    }


}
