package com.multimedia.yihuishou.utils.imageloader.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 圆角变换
 * Created by DZH on 16-12-22.
 */
public class RoundTransform extends BitmapTransformation {

    private float mRadius = 0f;

    public RoundTransform(Context context) {
        this(context, 4);
    }

    public RoundTransform(Context context, int dp) {
        super();
        mRadius = context.getResources().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int w, int h) {
        if (bitmap == null) return null;

        Bitmap result = bitmapPool.get(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rectF, mRadius, mRadius, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
