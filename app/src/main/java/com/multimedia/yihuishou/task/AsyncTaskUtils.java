
package com.multimedia.yihuishou.task;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.multimedia.yihuishou.log.LogUtils;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;


public abstract class AsyncTaskUtils {

    public static final int ASYNC_EXECUTOR_LEVEL_URGENT      = ThreadPoolManager.EXECUTOR_LEVEL_URGENT;
    public static final int ASYNC_EXECUTOR_LEVEL_IO          = ThreadPoolManager.EXECUTOR_LEVEL_IO;
    public static final int ASYNC_EXECUTOR_LEVEL_NETWORK     = ThreadPoolManager.EXECUTOR_LEVEL_NETWORK;
    public static final int ASYNC_EXECUTOR_LEVEL_IMAGEWORKER = ThreadPoolManager.EXECUTOR_LEVEL_IMAGE;
    public static final int ASYNC_EXECUTOR_MSG               = 100;


    public static Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == ASYNC_EXECUTOR_MSG && msg.obj != null) {
                if (msg.obj instanceof Runnable) {
                    exeIOTask((Runnable) msg.obj);
                }
                return true;
            }
            return false;
        }
    };

    public static Handler mUIHandler = new Handler(Looper.getMainLooper(), mCallback);

    public static void removeCallbacks(Runnable run) {
        if (mUIHandler != null) {
            mUIHandler.removeCallbacks(run);
        }
    }

    public static void runOnUIHandler(Runnable run) {
        mUIHandler.post(run);
    }

    public static void runOnUIHandler(Runnable run, long delayMillis) {
        mUIHandler.postDelayed(run, delayMillis);
    }

    /**
     * @param run io线程执行的任务
     */
    public static void runOnIOThread(Runnable run) {
        Message message = Message.obtain(mUIHandler, ASYNC_EXECUTOR_MSG, run);
        mUIHandler.sendMessage(message);
    }

    /**
     * @param run io线程执行的任务
     */
    public static void runOnIOThread(Runnable run, long delayMillis) {
        Message message = Message.obtain(mUIHandler, ASYNC_EXECUTOR_MSG, run);
        mUIHandler.sendMessageDelayed(message, delayMillis);
    }


    public static <Params, Progress, Result> void exe(int level, AsyncTask<Params, Progress, Result> asyncTask, Params... params) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                asyncTask.executeOnExecutor(ThreadPoolManager.getThreadPoolExecutor(level), params);
            } else {
                asyncTask.execute(params);
            }
        } catch (RejectedExecutionException e) {
            LogUtils.v("async task pool full");
        }
    }

    public static <Params, Progress, Result> void exeNetWorkTask(AsyncTask<Params, Progress, Result> asyncTask, Params... params) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                asyncTask.executeOnExecutor(
                        ThreadPoolManager.getNetworkThreadPoolExecutor(), params);
            } else {
                asyncTask.execute(params);
            }
        } catch (RejectedExecutionException e) {
            LogUtils.v("async task pool full");
        }
    }

    public static <Params, Progress, Result> void exeIOTask(AsyncTask<Params, Progress, Result> asyncTask, Params... params) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                asyncTask.executeOnExecutor(
                        ThreadPoolManager.getIOThreadPoolExecutor(), params);
            } else {
                asyncTask.execute(params);
            }
        } catch (RejectedExecutionException e) {
            LogUtils.v("async task pool full");
        }
    }

    /**
     * 用于加载紧急的任务，比如用户当前操作触发的
     *
     * @param asyncTask
     * @param params
     * @param <Params>
     * @param <Progress>
     * @param <Result>
     */
    public static <Params, Progress, Result> void exeUrgentTask(AsyncTask<Params, Progress, Result> asyncTask, Params... params) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                asyncTask.executeOnExecutor(
                        ThreadPoolManager.getUrgentThreadPoolExecutor(), params);
            } else {
                asyncTask.execute(params);
            }
        } catch (RejectedExecutionException e) {
            LogUtils.v("async task pool full");
        }
    }

    public static Future<?> exeIOTask(Runnable r) {
        try {
            return ThreadPoolManager.getIOThreadPoolExecutor().submit(r);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    public static Future<?> exeNetWorkTask(Runnable r) {
        try {
            return ThreadPoolManager.getNetworkThreadPoolExecutor().submit(r);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    public static Future<?> exeUrgentTask(Runnable r) {
        try {
            return ThreadPoolManager.getUrgentThreadPoolExecutor().submit(r);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    public static Future<?> exePlayerTask(Runnable r) {
        try {
            return ThreadPoolManager.getPlayerThreadPoolExecutor().submit(r);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    public static void removeIoCallBack(Runnable runnable) {
        try {
            ThreadPoolManager.getIOThreadPoolExecutor().remove(runnable);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

}
