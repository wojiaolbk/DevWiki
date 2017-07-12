package net.devwiki.devwiki.module.view.animation.pic;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import net.devwiki.devwiki.R;
import net.devwiki.devwiki.module.view.animation.BezierCircle;

import java.util.ArrayList;


public class VoiceAnimImageView extends ImageView {
    private static final int LINE_NUM = 9;
    private static final int LINE_OFFSET_SPACE = 12; // dp
    private static final int MAX_RADIUS_BEZIER_CIRCLE = 10;

    private int mWidth, mHeight;
    private boolean mIsNeedDrawMidCircle;
    private Bitmap mBitmap;
    private Paint mPaint;
    private BezierCircle mBezierCircle;
    private PaintFlagsDrawFilter mPaintFlagsDrawFilter;
    private ArrayList<Bitmap> mBitmapList = new ArrayList<>();

    private static final int[] IMAGE_ID = {
            R.drawable.ic_chat_record_wave_01,
            R.drawable.ic_chat_record_wave_02,
            R.drawable.ic_chat_record_wave_03,
            R.drawable.ic_chat_record_wave_04,
            R.drawable.ic_chat_record_wave_05,
    };

    public VoiceAnimImageView(Context context) {
        super(context);
        initView();
    }

    public VoiceAnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VoiceAnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering();
    }

    private void initView() {
        mIsNeedDrawMidCircle = false;
        mPaint = new Paint();
        mPaint.setColor(0xff1fb5fe);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mBezierCircle = new BezierCircle();
        mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);

        initBitmaps();
    }

    private void initBitmaps() {
        for (int i = 0; i < 5; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),IMAGE_ID[i]);
            mBitmapList.add(bitmap);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = right - left;
        mHeight = bottom - top;
        float radius = dpToPix(MAX_RADIUS_BEZIER_CIRCLE);
        mBezierCircle.initParams(mWidth, mHeight, radius);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) return;
        canvas.setDrawFilter(mPaintFlagsDrawFilter);

        canvas.save();
        drawBitmap(canvas, mBitmap, null);
        canvas.restore();

        canvas.setDrawFilter(null);
    }

    private Rect mSrcRect = new Rect();
    private Rect mDestRect = new Rect();
    private void drawBitmap(Canvas canvas, Bitmap bitmap, Paint paint) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        mSrcRect.set(0, 0, width, height);
        mDestRect.set(
                (mWidth - width) / 2, (mHeight - height) / 2,
                (mWidth + width) / 2, (mHeight + height) / 2);
        canvas.drawBitmap(bitmap, mSrcRect, mDestRect, paint);
    }


    private int mFrame = -1;
    public void startRecordingVoiceAnim(float voiceValue) {

        ValueAnimator frameAnim = ValueAnimator.ofInt(0,1000);
        frameAnim.setDuration(1000);
        frameAnim.setRepeatMode(ValueAnimator.RESTART);
        frameAnim.setRepeatCount(ValueAnimator.INFINITE);
        frameAnim.setInterpolator(new LinearInterpolator());
        frameAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)animation.getAnimatedValue();
                int frame = value / 200;
                if (mFrame != frame) {
                    mFrame = frame;
                    mBitmap = mBitmapList.get(mFrame % 5);
                    invalidate();
                }
            }
        });

        frameAnim.start();

    }



    private float dpToPix(float dp) {
        return dp * getResources().getDisplayMetrics().density;
    }
}
