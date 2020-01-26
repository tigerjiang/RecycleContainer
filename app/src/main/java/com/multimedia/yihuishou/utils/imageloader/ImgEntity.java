package com.multimedia.yihuishou.utils.imageloader;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.RequestListener;
import com.multimedia.yihuishou.R;


public class ImgEntity {

    /** 图片背景资源 */
    private int bgRes;
    /** 加载图片时的资源 */
    private int loadingRes;
    /** 加载失败时的资源 */
    private int loadFailRes = R.drawable.gray_default;
    /** 是否是GIF图片 */
    private boolean isGif;
    /** 图形转换 */
    private Transformation transformation;
    /** 监听器 */
    private RequestListener<Drawable> listener;
    /** GIF监听器 */
    private RequestListener<Drawable> gifListener;

    public int getBgRes() {
        return bgRes;
    }

    public void setBgRes(int bgRes) {
        this.bgRes = bgRes;
    }

    public int getLoadingRes() {
        return loadingRes;
    }

    public void setLoadingRes(int loadingRes) {
        this.loadingRes = loadingRes;
    }

    public int getLoadFailRes() {
        return loadFailRes;
    }

    public void setLoadFailRes(int loadFailRes) {
        this.loadFailRes = loadFailRes;
    }

    public boolean isGif() {
        return isGif;
    }

    public void setGif(boolean gif) {
        isGif = gif;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }

    public RequestListener<Drawable> getListener() {
        return listener;
    }

    public void setListener(RequestListener<Drawable> listener) {
        this.listener = listener;
    }

    public RequestListener<Drawable> getGifListener() {
        return gifListener;
    }

    public void setGifListener(RequestListener<Drawable> gifListener) {
        this.gifListener = gifListener;
    }
}
