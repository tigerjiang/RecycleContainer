package com.multimedia.yihuishou.utils.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.FileUtils;
import android.os.StatFs;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.multimedia.yihuishou.log.LogUtils;

import java.io.File;
import java.lang.reflect.Field;

/**
 * 图片加载配置类
 * @author DZH 2016年12月07日
 * @description
 */
public class GlideModuleConfig implements GlideModule {

    private static final String PATH_GLIDE = "glide";
    private static final int MEMORY_CACHE_SIZE = 1024 * 1024 * 13;
    private static final int BITMAP_CACHE_SIZE = 1024 * 1024 * 10;
    private static final int MIN_DISK_CACHE_SIZE = 100 * 1024 * 1024; // 100MB
    private static final int MAX_DISK_CACHE_SIZE = 200 * 1024 * 1024; // 200MB

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();

        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一

        //设置图片解码方式
        DecodeFormat format = DecodeFormat.PREFER_RGB_565;
        glideBuilder.setDefaultRequestOptions(new RequestOptions().format(format));
        glideBuilder.setDefaultTransitionOptions(Drawable.class, DrawableTransitionOptions.withCrossFade());
        //设置内存大小
        //glideBuilder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        glideBuilder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        //设置图片缓存大小
        // glideBuilder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));
        glideBuilder.setBitmapPool(new LruBitmapPool(memoryCacheSize));
        //设置磁盘缓存大小
        glideBuilder.setDiskCache(new DiskLruCacheFactory(getImageCachePath(context),
                calculateDiskCacheSize(getImageCachePath(context))));
        glideBuilder.setSourceExecutor(GlideExecutor.newSourceExecutor());
    }

    private int calculateDiskCacheSize(String dir) {
        int size = MIN_DISK_CACHE_SIZE;

        try {
            StatFs statFs = new StatFs(dir);
            int available = statFs.getBlockCount() * statFs.getBlockSize();
            size = available / 50;
        } catch (IllegalArgumentException ignored) {
        }

        return Math.max(Math.min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE);
    }

    public static void setDecodeFormat(Context context, int quality) {
        try {
            Field decodeFormat = Glide.class.getDeclaredField("decodeFormat");
            decodeFormat.setAccessible(true);
            decodeFormat.set(Glide.get(context), quality == 1 ? DecodeFormat.PREFER_ARGB_8888 : DecodeFormat
                    .PREFER_RGB_565);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }

    public static String getImageCachePath(Context context){
        String imagePath = context.getExternalCacheDir() + "/images/";
        File file = new File(imagePath);
        boolean isCreateSuccess = false;
        if (!file.exists()){
            isCreateSuccess = file.mkdir();
        }
        if (!isCreateSuccess){
            LogUtils.d(LogUtils.TAG, "创建图片缓存文件夹失败! ! !");
        }
        return imagePath;
    }

}
