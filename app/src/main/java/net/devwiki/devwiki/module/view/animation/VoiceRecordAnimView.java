package net.devwiki.devwiki.module.view.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class VoiceRecordAnimView extends ImageView {
    private static final int LINE_NUM = 9;
    private static final int LINE_OFFSET_SPACE = 12; // dp
    private static final int MAX_RADIUS_BEZIER_CIRCLE = 10;
    private float mRadius;
    private int mOffset;
    private int mWidth, mHeight;
    private boolean mIsNeedDrawMidCircle;
    private boolean mIsNeedDrawBezierCircle = false;
    private Paint mPaint;
    private BezierCircle mBezierCircle;
    private AnimatorSet mSendVoiceAnimSet;
    private ValueAnimator mBezierCircleAnim;
    private PaintFlagsDrawFilter mPaintFlagsDrawFilter;
    private ArrayList<Line> mLineList = new ArrayList<>();
    private ArrayList<AnimatorSet> mActivatedAnimSetList = new ArrayList<>();

    public VoiceRecordAnimView(Context context) {
        super(context);
        initView();
    }

    public VoiceRecordAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VoiceRecordAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering();
    }

    private void initView() {
        mOffset = 0;
        mIsNeedDrawMidCircle = false;
        mPaint = new Paint();
        mPaint.setColor(0xff1fb5fe);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        for (int i = 0; i < LINE_NUM; i++) {
            Line line = new Line();
            mLineList.add(line);
        }
        mBezierCircle = new BezierCircle();
        mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
    }

    public void setOffset(int offset) {
        this.mOffset = offset;
    }

    public void initVoiceLines() {
        int size = mLineList.size();
        for(int i = 0; i < size; i++) {
            Line line = mLineList.get(i);
            line.x =  mWidth / 2 - (size / 2 - i) * dpToPix(LINE_OFFSET_SPACE);
            line.y = mHeight / 2 - mOffset;
            line.height = 0.01f;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = right - left;
        mHeight = bottom - top;
        float radius = dpToPix(MAX_RADIUS_BEZIER_CIRCLE);
        mBezierCircle.initParams(mWidth, mHeight, radius);
        initVoiceLines();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(mPaintFlagsDrawFilter);

        for (Line line: mLineList) {
            canvas.save();
            canvasDrawLine(canvas, line);
            canvas.restore();
        }

        canvas.setDrawFilter(null);
    }

    private void canvasDrawLine(Canvas canvas, Line line) {
        float startX = line.x;
        float startY = line.y - line.height / 2;
        float endX = line.x;
        float endY = line.y + line.height / 2;
        canvas.drawLine(startX, startY, endX, endY, mPaint);
    }

    /**
     *   start Anim of recording Voice
     * @param  voiceValue: value of voice
     */
    public void startRecordingVoiceAnim(float voiceValue) {
        cancelRecordingVoiceAnim();
        mIsNeedDrawMidCircle = false;
        mIsNeedDrawBezierCircle = false;
        final AnimatorSet animSet = createRecordingVoiceAnim(voiceValue);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mActivatedAnimSetList.add(animSet);
            }
        });
        animSet.start();
    }

    Random mRandom = new Random(System.currentTimeMillis());
    private AnimatorSet createRecordingVoiceAnim(float voiceValue) {

        List<Animator> animList = new ArrayList<>();
        final AnimatorSet animSet = new AnimatorSet();
        int size = mLineList.size();
        for(int i = 0; i < 3 * size / 3; i++) {
            float value = voiceValue / (mRandom.nextInt(LINE_NUM / 2) + 1);
            ValueAnimator anim = createVoiceLineAnim(mLineList.get(mRandom.nextInt(LINE_NUM)), value);
            animList.add(anim);
        }

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
                    invalidate();
                }
            }
        });
        animList.add(frameAnim);

        animSet.playTogether(animList);
        return animSet;
    }


    private int mFrame = -1;

    private ValueAnimator createVoiceLineAnim(final Line line, float height) {

        int time = 300;
        ValueAnimator anim = ValueAnimator.ofFloat(0.01f, height);
        anim.setDuration(time);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setInterpolator(new PathInterpolator(0.455f, 0.03f, 0.515f, 0.955f));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                line.height = (float)valueAnimator.getAnimatedValue();
            }
        });
        return anim;
    }

    /**
     *   cancel Anim of sending Voice
     */
    public void cancelRecordingVoiceAnim() {
        Iterator<AnimatorSet> iterator = mActivatedAnimSetList.iterator();
        while(iterator.hasNext()){
            AnimatorSet animSet = iterator.next();
            if (animSet != null) {
                animSet.cancel();
                iterator.remove();
            }
        }
    }

    /**
     *   start Anim of sending Voice
     */
    public void startSendVoiceAnim() {
        cancelRecordingVoiceAnim();
        cancelSendVoiceAnim();
        initVoiceLines();
        mIsNeedDrawBezierCircle = false;
        mSendVoiceAnimSet = createSendVoiceAnim();
        mSendVoiceAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsNeedDrawMidCircle = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsNeedDrawMidCircle = false;
                startBezierCircleAnim();
            }
        });
        mSendVoiceAnimSet.start();
    }

    private AnimatorSet createSendVoiceAnim() {
        int delayTime = 100;
        List<Animator> AnimList = new ArrayList<>();
        AnimatorSet animSet = new AnimatorSet();

        int size = mLineList.size();
        for(int i = 0; i < size; i++) {
            ValueAnimator anim = createLineTransAnim(mLineList.get(i));
            anim.setStartDelay(Math.abs(size / 2 - i) * delayTime);
            AnimList.add(anim);
        }
        AnimList.add(createMidPointAnim());
        animSet.playTogether(AnimList);
        return animSet;
    }

    private ValueAnimator createLineTransAnim(final Line line) {
        int time = 100;
        ValueAnimator anim = ValueAnimator.ofFloat(line.x, mWidth / 2);
        anim.setDuration(time);
        anim.setInterpolator(new PathInterpolator(0.25f, 0.46f, 0.45f, 0.94f));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                line.x = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        return anim;
    }

    private ValueAnimator createMidPointAnim() {
        int time = 400;
        float radius = dpToPix(MAX_RADIUS_BEZIER_CIRCLE);
        ValueAnimator anim = ValueAnimator.ofFloat(0, radius);
        anim.setDuration(time);
        anim.setInterpolator(new PathInterpolator(0.68f, 0.1f, 0.265f, 2.0f));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mRadius = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        return anim;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startBezierCircleAnim() {
        if (mBezierCircleAnim != null && mBezierCircleAnim.isRunning()) {
            mBezierCircleAnim.cancel();
            mBezierCircleAnim = null;
        }

        int time = 250;
        float radius = dpToPix(MAX_RADIUS_BEZIER_CIRCLE);
        mBezierCircle.initParams(mWidth, mHeight, radius);
        mIsNeedDrawBezierCircle = true;
        mBezierCircleAnim = ValueAnimator.ofFloat(0.0f, 1.0f);
        mBezierCircleAnim.setDuration(time);
        mBezierCircleAnim.setInterpolator(new PathInterpolator(0.25f, 0.46f, 0.45f, 0.94f));
        mBezierCircleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float)valueAnimator.getAnimatedValue();
                mBezierCircle.updateBezierCircle(value , mOffset);
                invalidate();
            }
        });
        mBezierCircleAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(mCallback != null) {
                    mCallback.onAnimFinish();

                }
                VoiceRecordAnimView.this.setBackgroundColor(0xff000000);
            }
        });
        mBezierCircleAnim.start();
    }

    /**
     *   cancel Anim of sending Voice
     */
    public void cancelSendVoiceAnim() {
        if (mSendVoiceAnimSet != null && mSendVoiceAnimSet.isRunning()) {
            mSendVoiceAnimSet.cancel();
            mSendVoiceAnimSet = null;
        }

        if (mBezierCircleAnim != null && mBezierCircleAnim.isRunning()) {
            mBezierCircleAnim.cancel();
            mBezierCircleAnim = null;
        }

    }

    private float dpToPix(float dp) {
        return dp * getResources().getDisplayMetrics().density;
    }

    /**
     *   voice line
     */
    private static class Line {
        float height;
        float x, y;
    }


    private AnimFinishCallback mCallback;
    public void setAnimFinishCallBack (AnimFinishCallback callback) {
        mCallback = callback;
    }

    public void removeAnimFinishCallBack () {
        mCallback = null;
    }

    public interface AnimFinishCallback {
        void onAnimFinish();
    }

}
