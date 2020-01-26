package com.multimedia.yihuishou.utils.imageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.multimedia.yihuishou.log.LogUtils;
import com.multimedia.yihuishou.task.AsyncTaskUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片加载工具类
 * Created by DZH on 16-12-23.
 * 暂时提供Glade，FinalBitmap图片加载能力，需要在Application中初始化
 */
public class ImgUtils {

    private static final String TAG = "ImgUtils";
    /** 类型——0：Glide图片加载架框 */
    public static final int TYPE_GLIDE = 0;
    /** 类型——1：FinalBitmap图片加载架框 */
    public static final int TYPE_FINALBITMAP = 1;

    private static int mType = TYPE_GLIDE;

    private static RequestListener<Drawable> eListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            LogUtils.d(TAG, "onException", "model=" + model + "  isFirstResource=" + isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            return false;
        }
    };

    private static RequestListener<Drawable> eGifListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            LogUtils.d(TAG, "onException", "model=" + model + "  isFirstResource=" + isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            return false;
        }
    };
    //    private static FinalBitmap mFB;
    //    private static Map<String, BitmapDisplayConfig> mConfigMap;

    /**
     * 加载图片,带监听回调
     *
     * @param v 图片组件
     * @param listener 图片加载回调函数
     * @param url 图片url链接
     */
    public static void load(ImageView v, String url,
                            RequestListener<Drawable> listener) {
        load(v, url, 0, 0, 0, false, null, listener, null);
    }

    /**
     * 加载图片
     * @param v   图片组件
     * @param url 图片地址
     */
    public static void load(ImageView v, String url) {
        load(v, url, 0, 0, 0, false, null, null, null);
    }
    /**
     * 加载本地gif
     * @param v   图片组件
     * @param file gif文件
     */
    public static void load(ImageView v, File file) {
        Glide.with(v.getContext())
                .setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                .load(file)
                .into(v);
    }
    /**
     * 加载图片
     * @param v     图片组件
     * @param url   图片地址
     * @param bgRes 背景图片资源
     */
    public static void load(ImageView v, String url, int bgRes) {
        load(v, url, bgRes, 0, 0, false, null, null, null);
    }

    /**
     * 加载图片
     * @param v            图片组件
     * @param url          图片地址
     * @param bgRes        背景图片资源
     * @param loadingBgRes 加载背景图片资源
     * @param loadFailRes  加载失败资源
     */
    public static void load(ImageView v, String url, int bgRes, int loadingBgRes, int loadFailRes) {
        load(v, url, bgRes, loadingBgRes, loadFailRes, false, null, null, null);
    }

    /**
     * 加载图片
     * @param v     图片组件
     * @param url   图片地址
     * @param isGif 是否为GIF图
     */
    public static void load(ImageView v, String url, boolean isGif) {
        load(v, url, 0, 0, 0, isGif, null, null, null);
    }

    /**
     * 加载图片
     * @param v     图片组件
     * @param url   图片地址
     * @param bgRes 背景图片资源
     * @param isGif 是否为GIF图
     */
    public static void load(ImageView v, String url, int bgRes, boolean isGif) {
        load(v, url, bgRes, 0, 0, isGif, null, null, null);
    }

    /**
     * 加载图片
     * @param v            图片组件
     * @param url          图片地址
     * @param bgRes        背景图片资源
     * @param loadingBgRes 加载背景图片资源
     * @param loadFailRes  加载失败资源
     * @param isGif        是否为GIF图
     */
    public static void load(ImageView v, String url, int bgRes, int loadingBgRes, int loadFailRes, boolean isGif) {
        load(v, url, bgRes, loadingBgRes, loadFailRes, isGif, null, null, null);
    }

    public static void load(ImageView v, String url, boolean isGif,
                            RequestListener<Drawable> listener,
                            RequestListener<Drawable> gifListener) {
        load(v, url, 0, 0, 0, isGif, null, listener, gifListener);
    }

    public static void load(ImageView v, String url, boolean isGif,int bgRes,
                            RequestListener<Drawable> listener,
                            RequestListener<Drawable> gifListener) {
        load(v, url, bgRes, 0, 0, isGif, null, listener, gifListener);
    }

    /**
     * 加载图片
     * @param v           图片组件
     * @param url         图片地址
     * @param bgRes       背景图片资源
     * @param loadingRes  加载图片资源
     * @param loadFailRes 加载失败资源
     * @param isGif       是否为GIF图
     * @param tf          图形变换
     * @param listener
     * @param gifListener
     */
    public static void load(ImageView v, String url, int bgRes, int loadingRes, int loadFailRes, boolean isGif,
                            Transformation tf, RequestListener<Drawable> listener,
                            RequestListener<Drawable> gifListener) {
        ImgEntity entity = new ImgEntity();
        entity.setBgRes(bgRes);
        entity.setLoadingRes(loadingRes);
        entity.setLoadFailRes(loadFailRes);
        entity.setGif(isGif);
        entity.setTransformation(tf);
        entity.setListener(listener);
        entity.setGifListener(gifListener);
        load(v, url, entity);
    }

    /**
     * 加载图片
     * @param v      图片组件
     * @param url    图片地址
     * @param entity 图片配置对象
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void load(ImageView v, String url, ImgEntity entity) {
        if (v == null || entity == null) return;
        if (entity.getBgRes() > 0) {
            v.setBackgroundResource(entity.getBgRes());
        } else {
            v.setBackground(null);
        }
        if (TextUtils.isEmpty(url)) url = "";
        if (TYPE_FINALBITMAP == mType) {
            //            if (entity.getLoadFailRes() == 0) {
            //                mFB.display(v, url);
            //            } else {
            //                BitmapDisplayConfig config = mConfigMap.get("" + entity.getLoadFailRes());
            //                if (config == null) {
            //                    config = new BitmapDisplayConfig();
            //                    config.setAnimationType(1);
            //                    if (entity.getLoadingRes() != 0) {
            //                        config.setLoadingBitmap(BitmapFactory.decodeResource(mContext.getResources(),
            // entity
            //                                .getLoadingRes()));
            //                    }
            //                    if (entity.getLoadFailRes() != 0) {
            //                        config.setLoadfailBitmap(BitmapFactory.decodeResource(mContext.getResources(),
            // entity
            //                                .getLoadFailRes()));
            //                    }
            //                    mConfigMap.put("" + entity.getLoadFailRes(), config);
            //                }
            //                mFB.display(v, url, config);
            //            }
        } else {

            if (entity.getTransformation() == null) {
                if (entity.isGif()) {
                    Glide.with(v.getContext())
                            .setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                            .load(url)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(entity.getLoadingRes())
                            .error(entity.getLoadFailRes())
                            .listener((RequestListener<Drawable>) (entity.getGifListener()
                                    == null ? eGifListener : entity.getGifListener()))
                            .into(v);

                } else {
                    Glide.with(v.getContext())
                            .load(url)
                            .placeholder(entity.getLoadingRes())
                            .error(entity.getLoadFailRes())
                            .listener((RequestListener<Drawable>) (entity.getListener()
                                    == null ? eListener : entity.getListener()))
                            .into(v);
                }
            } else {
                Glide.with(v.getContext())
                        .load(url)
                        .placeholder(entity.getLoadingRes())
                        .error(entity.getLoadFailRes())
                        .transform(entity.getTransformation())
                        .into(v);
            }
        }

    }

    public static void loadmineImage(ImageView v, String url) {
        ImgEntity entity = new ImgEntity();
        if (v == null || entity == null) return;
        if (entity.getBgRes() > 0) {
            v.setBackgroundResource(entity.getBgRes());
        } else {
            v.setBackground(null);
        }
        if (TextUtils.isEmpty(url)) url = "";


        if (entity.getTransformation() == null) {
            Glide.with(v.getContext())
                    .load(url)
                    .placeholder(entity.getLoadingRes())
                    .error(entity.getLoadFailRes())
                    .into(v);
        } else {
            Glide.with(v.getContext())
                    .load(url)
                    .placeholder(entity.getLoadingRes())
                    .error(entity.getLoadFailRes())
                    .transform(entity.getTransformation())
                    .into(v);
        }
    }


    public static void saveToFileImp(final String filepath, final Bitmap bitmap) {
        if (bitmap == null || TextUtils.isEmpty(filepath)) {
            Log.d("dlna", "saveToFileImp  filepath  isEmpty");
            return;
        }

        AsyncTaskUtils.exeIOTask(new Runnable() {
            @Override
            public void run() {
                File cahceFile = new File(filepath);
                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(cahceFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.e("dlna", e);
                    Log.e("dlna", "saveToFileImp  Exception  filepath == " + filepath);
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 通过图片的绝对路径来获取对应的压缩后的Bitmap对象
     */
    public static Bitmap getCompressedBitmap(String filePath, int requireWidth, int requireHeight) {
        // 第一次解析将inJustDecodeBounds设置为true,用以获取图片大小,并且不需要将Bitmap对象加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options); // 第一次解析
        // 计算inSampleSize的值,并且赋值给Options.inSampleSize
        options.inSampleSize = calculateInSampleSize(options, requireWidth, requireHeight);
        // 使用获取到的inSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 通过图片资源id获取图片对应的压缩后的Bitmap对象
     */
    public static Bitmap getCompressedBitmap(Resources res, int resId, int requiredWidth, int requiredHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);// 第一次解析
        options.inSampleSize = calculateInSampleSize(options, requiredWidth, requiredHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);// 第一次解析
    }

    /**
     * 计算压缩的inSampleSize的值,该值会在宽高上都进行压缩(也就是压缩前后比例是inSampleSize的平方倍)
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int requireWidth, int requireHeight) {

        if (options == null || requireWidth == 0 || requireHeight == 0) {
            return 1;
        }

        // 获取源图片的实际的宽度和高度
        int realWidth = options.outWidth;
        int realHeight = options.outHeight;

        int inSampleSize = 1;
        if (realWidth > requireWidth || realHeight > requireHeight) {
            // 计算出实际的宽高与目标宽高的比例
            int widthRatio = Math.round((float) realWidth / (float) requireWidth);
            int heightRatio = Math.round((float) realHeight / (float) requireHeight);
            // 选择宽高比例最小的值赋值给inSampleSize,这样可以保证最终图片的宽高一定会大于或等于目标的宽高
            inSampleSize = widthRatio < heightRatio ? widthRatio : heightRatio;
        }
        return inSampleSize;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, int maxSize) {
        Bitmap result = bitmap;
        float scale = 1;
        while (result.getAllocationByteCount() > maxSize) {
            if (result != bitmap) {
                result.recycle();
            }
            scale -= 0.1f;
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,true);

        }
        return result;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, int outWidth, int outHeight) {

        Bitmap result;
        int oriWidth = bitmap.getWidth();
        int oriHeight = bitmap.getHeight();
        int diffWidth = Math.abs(oriWidth - outWidth);
        int diffHeight = Math.abs(oriHeight - outHeight);
        float scale;
        if (diffWidth < diffHeight) {
            scale = (float) outWidth / (float) oriWidth;
        } else {
            scale = (float) outHeight / (float) oriHeight;
        }

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);

        result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,true);
        return result;

    }
}
