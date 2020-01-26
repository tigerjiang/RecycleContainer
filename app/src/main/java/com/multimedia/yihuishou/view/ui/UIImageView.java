package com.multimedia.yihuishou.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.multimedia.yihuishou.R;
import com.multimedia.yihuishou.log.LogUtils;

/**
 * UI——ImageView（可圆形、圆角）
 * Created by DZH on 16-12-22.
 */
public class UIImageView extends AppCompatImageView {

    private static final String TAG = "UIImageView";

    /** 类型——0：默认 */
    public static final int TYPE_NORMAL = 0;
    /** 类型——1：边框 */
    public static final int TYPE_BORDER = 1;
    /** 类型——2：圆形 */
    public static final int TYPE_CIRCLE = 2;
    /** 类型——3：椭圆形 */
    public static final int TYPE_OVAL = 3;
    /** 类型——4：圆角 */
    public static final int TYPE_ROUND = 4;
    /** 类型——5：多边形 */
    public static final int TYPE_SHAPE = 5;

    /** 图片类型 */
    private int mType = TYPE_NORMAL;

    /** 边框宽度 */
    private int mBorderWidth;
    /** 边框颜色 */
    private int mBorderColor;
    /** 圆角大小 */
    private int mRound;
    /** 多边形坐标数组 */
    private Path mPath;

    public UIImageView(Context context) {
        this(context, null, 0);
    }

    public UIImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UIImageView);
            mType = array.getInteger(R.styleable.UIImageView_uiType, TYPE_NORMAL);
            mBorderWidth = array.getDimensionPixelOffset(R.styleable.UIImageView_uiBorderWidth, 0);
            mBorderColor = array.getColor(R.styleable.UIImageView_uiBorderColor, Color.WHITE);
            mRound = array.getDimensionPixelSize(R.styleable.UIImageView_uiRound, 0);
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null && mType > TYPE_NORMAL && mType <= TYPE_SHAPE) {
            //保存图层
//            int saveFlags = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
//                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
            int saveFlags = Canvas.ALL_SAVE_FLAG;
            canvas.saveLayer(0, 0, getWidth(), getHeight(), null, saveFlags);

            //绘制底层图形
            final Paint paint = getFillPaint();
            if (TYPE_BORDER == mType) {
                drawBorder(canvas, paint);
            } else if (TYPE_CIRCLE == mType) {
                drawCircle(canvas, paint);
            } else if (TYPE_OVAL == mType) {
                drawOval(canvas, paint);
            } else if (TYPE_ROUND == mType) {
                drawRound(canvas, paint);
            } else if (TYPE_SHAPE == mType) {
                drawShape(canvas, paint);
            }

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            //绘制图片
            Bitmap srcBitmap = getBitmap(drawable);
            if (srcBitmap == null) return;
            canvas.drawBitmap(srcBitmap, getMatrix(srcBitmap.getWidth(), srcBitmap.getHeight(), getWidth(), getHeight
                    ()), paint);

            //绘制边线
            if (mBorderWidth > 0) {
                paint.setStrokeWidth(mBorderWidth);
                paint.setColor(mBorderColor);
                paint.setStyle(Paint.Style.STROKE);
                if (TYPE_BORDER == mType) {
                    drawBorder(canvas, paint);
                } else if (TYPE_CIRCLE == mType) {
                    drawCircle(canvas, paint);
                } else if (TYPE_OVAL == mType) {
                    drawOval(canvas, paint);
                } else if (TYPE_ROUND == mType) {
                    drawRound(canvas, paint);
                } else if (TYPE_SHAPE == mType) {
                    drawShape(canvas, paint);
                }
            }

            canvas.restore();
            if (drawable instanceof BitmapDrawable) { //bitmap为原图不进行回收
            } else if (srcBitmap != null && !srcBitmap.isRecycled()) {
                srcBitmap.recycle();
                srcBitmap = null;
            }
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 绘制边线
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawBorder(Canvas canvas, Paint paint) {
        canvas.drawRect(new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() -
                getPaddingBottom()), paint);
    }

    /**
     * 绘制圆形
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawCircle(Canvas canvas, Paint paint) {
        canvas.drawCircle(getPaddingLeft() + (getWidth() - getPaddingLeft() - getPaddingRight()) / 2, getPaddingTop()
                + (getHeight() - getPaddingTop() - getPaddingBottom()) / 2, Math.min(getWidth() - getPaddingLeft() -
                getPaddingRight(), getHeight() - getPaddingTop() - getPaddingBottom()) / 2, paint);
    }

    /**
     * 绘制椭圆
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawOval(Canvas canvas, Paint paint) {
        canvas.drawOval(new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() -
                getPaddingBottom()), paint);
    }

    /**
     * 绘制圆角
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawRound(Canvas canvas, Paint paint) {
        if (mRound < 0) mRound = 0;
        canvas.drawRoundRect(new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight()
                - getPaddingBottom()), mRound, mRound, paint);
    }

    /**
     * 绘制多边形
     * @param canvas 画布
     * @param paint  画笔
     */
    private void drawShape(Canvas canvas, Paint paint) {
        if (mPath != null) canvas.drawPath(mPath, paint);
    }

    /**
     * 获取实心画笔
     * @return
     */
    protected Paint getFillPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    /**
     * 获取空心画笔
     * @return
     */
    private Paint getStrokePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(mBorderWidth);
        paint.setColor(mBorderColor);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    /**
     * 获取需要显示的图片
     * @param drawable 图形
     * @return
     */
    private Bitmap getBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config
                .RGB_565;
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(w, h, config);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
            drawable.draw(canvas);
        } catch (Exception e) {
            LogUtils.e(TAG, e);
        }
        return bitmap;
    }

    /**
     * 获取矩阵模型
     * @param bmpW 图片宽
     * @param bmpH 图片高
     * @param w    容器宽
     * @param h    容器高
     * @return
     */
    private Matrix getMatrix(float bmpW, float bmpH, float w, float h) {
        Matrix matrix = new Matrix();
        ScaleType scaleType = getScaleType();
        if (scaleType == ScaleType.CENTER) {
            float dx = (w - bmpW) / 2;
            float dy = (h - bmpH) / 2;
            matrix.setTranslate(dx, dy);
        } else if (scaleType == ScaleType.CENTER_CROP) {
            float ratio = Math.max(w / bmpW, h / bmpH);
            float useWidth = bmpW * ratio;
            float useHeight = bmpH * ratio;
            matrix.setTranslate((w - useWidth) / 2, (h - useHeight) / 2);
            matrix.preScale(ratio, ratio);
        } else if (scaleType == ScaleType.CENTER_INSIDE) {
            float ratio = Math.min(w / bmpW, h / bmpH);
            if (ratio > 1) ratio = 1;
            float useWidth = bmpW * ratio;
            float useHeight = bmpH * ratio;
            matrix.setTranslate((w - useWidth) / 2, (h - useHeight) / 2);
            matrix.preScale(ratio, ratio);
        } else if (scaleType == ScaleType.FIT_CENTER) {
            float ratio = Math.min(w / bmpW, h / bmpH);
            float useWidth = bmpW * ratio;
            matrix.setTranslate((w - useWidth) / 2, 0);
            matrix.preScale(ratio, ratio);
        } else if (scaleType == ScaleType.FIT_END) {
            float ratio = Math.min(w / bmpW, h / bmpH);
            float useWidth = bmpW * ratio;
            matrix.setTranslate(w - useWidth, 0);
            matrix.preScale(ratio, ratio);
        } else if (scaleType == ScaleType.FIT_START) {
            float ratio = Math.min(w / bmpW, h / bmpH);
            matrix.setScale(ratio, ratio);
        } else if (scaleType == ScaleType.FIT_XY) {
            float wRatio = w / bmpW;
            float hRatio = h / bmpH;
            matrix.setScale(wRatio, hRatio);
        } else if (scaleType == ScaleType.MATRIX) {
            //do nothing
        }
        return matrix;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int px) {
        mBorderWidth = px;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int color) {
        mBorderColor = color;
    }

    public int getRound() {
        return mRound;
    }

    public void setRound(int round) {
        mRound = round;
    }

    public Path getPath() {
        return mPath;
    }

    public void setPath(Path path) {
        mPath = path;
    }
}
