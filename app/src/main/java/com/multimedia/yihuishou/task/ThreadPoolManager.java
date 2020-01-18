package com.multimedia.yihuishou.task;


import com.multimedia.yihuishou.log.LogUtils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolManager {
    public static final  String TAG                                   = "ThreadPoolManager";
    public static final  int    EXECUTOR_LEVEL_IO                     = 0;//用于一般的io请求
    public static final  int    EXECUTOR_LEVEL_NETWORK                = 1;//用于一般的网络请求
    public static final  int    EXECUTOR_LEVEL_URGENT                 = 2;//用于加载紧急的，比如用户当前操作触发的
    public static final  int    EXECUTOR_LEVEL_PLAYER                 = 3;//用于异步处理播放器任务
    public static final  int    EXECUTOR_LEVEL_IMAGE                  = 4;//用于加载图片
    public static final  int    EXECUTOR_LEVEL_OFFLINE                = 5;//用于离线统计
    public static final  int    EXECUTOR_LEVEL_STATISTICS_LOSS_SINGLE = 6; // 转化率打点线程，单线程池
    private static final int    EXECUTOR_LEVEL_NUM                    = EXECUTOR_LEVEL_STATISTICS_LOSS_SINGLE + 1; // 数量， 必须为最后一个 +1


    private static final int IO_KEEP_ALIVE_TIME      = 120; //单位是秒
    private static final int NETWORK_KEEP_ALIVE_TIME = 60; //单位是秒
    private static final int URGENT_KEEP_ALIVE_TIME  = 60; //单位是秒
    private static final int PLAYER_KEEP_ALIVE_TIME  = 10; //单位是秒
    public static final  int OFFLINE_KEEP_ALIVE_TIME = 120; //单位是秒

    private static final int IMAGE_KEEP_ALIVE_TIME = 15; //单位是秒

    private static boolean sHasInited = false;

    protected static ThreadPoolExecutor sExecutors[] = new ThreadPoolExecutor[EXECUTOR_LEVEL_NUM];

    private static final int mCpuCores = Runtime.getRuntime().availableProcessors();

    private static int mIoThreadCount = mCpuCores >= 4 ? mCpuCores : 4;

    private static int mNetworkThreadCount = mIoThreadCount;

    private static int mUrgentThreadCount = mIoThreadCount;

    public static int mOfflineThreadCount = mIoThreadCount;

    private static int mPlayerThreadCount = 1;

    private static int mImageThreadCount = mNetworkThreadCount;

    //处理被拒绝的任务，无限的等待队列
    private static ThreadPoolExecutor mRejectedExecutor = new ThreadPoolExecutor(1, 1, IMAGE_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), getThreadFactory("backup", Thread.NORM_PRIORITY));

    private static RejectedExecutionHandler mRejectedHandler = new RejectedExecutionHandler() {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LogUtils.d("Thread pool executor: reject work");
            if (mRejectedExecutor != null) {
                LogUtils.d("Thread pool executor: reject work, put into backup pool");
                mRejectedExecutor.execute(r);
            }
        }
    };

    public static ThreadPoolExecutor getIOThreadPoolExecutor() {
        initThreadPool(EXECUTOR_LEVEL_IO, mIoThreadCount, IO_KEEP_ALIVE_TIME, "io", Thread.NORM_PRIORITY);
        return sExecutors[EXECUTOR_LEVEL_IO];
    }

    public static ThreadPoolExecutor getNetworkThreadPoolExecutor() {
        initThreadPool(EXECUTOR_LEVEL_NETWORK, mNetworkThreadCount, NETWORK_KEEP_ALIVE_TIME, "network", Thread.NORM_PRIORITY);
        return sExecutors[EXECUTOR_LEVEL_NETWORK];
    }

    public static ThreadPoolExecutor getUrgentThreadPoolExecutor() {
        initThreadPool(EXECUTOR_LEVEL_URGENT, mUrgentThreadCount, URGENT_KEEP_ALIVE_TIME, "urgent", Thread.MAX_PRIORITY);
        return sExecutors[EXECUTOR_LEVEL_URGENT];
    }


    public static ThreadPoolExecutor getPlayerThreadPoolExecutor() {
        initThreadPool(EXECUTOR_LEVEL_PLAYER, mPlayerThreadCount, PLAYER_KEEP_ALIVE_TIME, "player", Thread.MAX_PRIORITY);
        return sExecutors[EXECUTOR_LEVEL_PLAYER];
    }

    public static ThreadPoolExecutor getThreadPoolExecutor(int level) {
        init(false);
        if (level <= EXECUTOR_LEVEL_NUM - 1 && level >= EXECUTOR_LEVEL_IO) {
            return sExecutors[level];
        } else {
            throw new IllegalArgumentException("level取值范围是[0,3]");
        }
    }

    protected static synchronized void initThreadPool(int index, int threadCount, int aliveTime, String name, int priority) {
        if (sExecutors == null) {
            sExecutors = new ThreadPoolExecutor[EXECUTOR_LEVEL_NUM];
        }
        if (index < 0 || index >= sExecutors.length) {
            return;
        }

        if (sExecutors[index] == null
                || sExecutors[index].isShutdown()
                || sExecutors[index].isTerminated()
                || sExecutors[index].isTerminating()) {
            LogUtils.trackerLog(TAG, "initThreadPool index　" + index);

            int maximumPoolSize = threadCount * 2;
            if (index == EXECUTOR_LEVEL_PLAYER || "player".equalsIgnoreCase(name)) {
                maximumPoolSize = threadCount;
            }

            sExecutors[index] = new ThreadPoolExecutor(
                    threadCount,
                    maximumPoolSize,
                    aliveTime,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    getThreadFactory(name, priority),
                    mRejectedHandler);
            sExecutors[index].allowCoreThreadTimeOut(true);
        }
    }


    public static synchronized void init(boolean forceInit) {
        if (!sHasInited || forceInit) {
            long currentTime = System.currentTimeMillis();
            LogUtils.trackerLog(TAG, "  init() 　");
            if (mRejectedExecutor == null
                    || mRejectedExecutor.isTerminating()
                    || mRejectedExecutor.isTerminated()
                    || mRejectedExecutor.isShutdown()) {
                mRejectedExecutor = new ThreadPoolExecutor(1, 1, IMAGE_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(), getThreadFactory("backup", Thread.NORM_PRIORITY));
            }
            mRejectedExecutor.allowCoreThreadTimeOut(true);

            if (sExecutors == null) {
                sExecutors = new ThreadPoolExecutor[EXECUTOR_LEVEL_IMAGE + 1];
            }
            //初始化IO线程池
            sExecutors[EXECUTOR_LEVEL_IO] = new ThreadPoolExecutor(
                    mIoThreadCount,
                    mIoThreadCount * 2,
                    IO_KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    getThreadFactory("io", Thread.NORM_PRIORITY),
                    mRejectedHandler);
            sExecutors[EXECUTOR_LEVEL_IO].allowCoreThreadTimeOut(true);
            //初始化NETWORK线程池

//            sExecutors[EXECUTOR_LEVEL_NETWORK] = new ThreadPoolExecutor(
//                    mNetworkThreadCount,
//                    mNetworkThreadCount,
//                    NETWORK_KEEP_ALIVE_TIME,
//                    TimeUnit.SECONDS,
//                    new LinkedBlockingQueue<Runnable>(mNetworkThreadCount * 15),
//                    getThreadFactory("network", Thread.NORM_PRIORITY),
//                    mRejectedHandler);
//            sExecutors[EXECUTOR_LEVEL_NETWORK].allowCoreThreadTimeOut(true);

            //初始化 player 线程池
            sExecutors[EXECUTOR_LEVEL_PLAYER] = new ThreadPoolExecutor(
                    mPlayerThreadCount,
                    mPlayerThreadCount,
                    PLAYER_KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    getThreadFactory("player", Thread.MAX_PRIORITY),
                    mRejectedHandler);
            sExecutors[EXECUTOR_LEVEL_PLAYER].allowCoreThreadTimeOut(false);

            //初始化图片线程池

//            sExecutors[EXECUTOR_LEVEL_IMAGE] = new ThreadPoolExecutor(
//                    mImageThreadCount,
//                    mImageThreadCount,
//                    IMAGE_KEEP_ALIVE_TIME,
//                    TimeUnit.SECONDS,
//                    new LinkedBlockingQueue<Runnable>(mImageThreadCount *10),
//                    getThreadFactory("image", Thread.NORM_PRIORITY),
//                    mRejectedHandler);
//            sExecutors[EXECUTOR_LEVEL_IMAGE].allowCoreThreadTimeOut(true);

            //初始化 STATISTICS_LOSS_SINGLE 线程池, 单线程
            sExecutors[EXECUTOR_LEVEL_STATISTICS_LOSS_SINGLE] = new ThreadPoolExecutor(
                    1,
                    1,
                    100,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    getThreadFactory("loss_statistics", Thread.MAX_PRIORITY),
                    mRejectedHandler);
            sExecutors[EXECUTOR_LEVEL_STATISTICS_LOSS_SINGLE].allowCoreThreadTimeOut(false);

            sHasInited = true;
            LogUtils.trackerLog(TAG, "initThreadPool 耗时　" + (System.currentTimeMillis() - currentTime));
        }

    }

    private static ThreadFactory getThreadFactory(final String name, final int priority) {
        return new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable r) {
                count++;
                Thread thr = new Thread(r, String.format("threadpool-%s-%d", name, count));
                thr.setDaemon(false);
                thr.setPriority(priority);
                return thr;
            }
        };
    }

}
