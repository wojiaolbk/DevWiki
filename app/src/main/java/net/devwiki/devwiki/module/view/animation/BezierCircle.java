package net.devwiki.devwiki.module.view.animation;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class BezierCircle {
    private static final float blackMagic = 0.50f;
    private float mRadius;
    private float mOffset;
    private float mAngle = 0;
    private int mWidth, mHeight;

    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    private VPoint mP2 = new VPoint();
    private VPoint mP4 = new VPoint();
    private HPoint mP1 = new HPoint();
    private HPoint mP3 = new HPoint();

    public void initParams(int width, int height, float radius) {
        mWidth = width;
        mHeight = height;
        mRadius = radius;
        mPaint.setColor(0xff1fb5fe);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        mOffset = mRadius * blackMagic;
    }

    public void drawBezierCircle(Canvas canvas) {
        canvas.rotate(mAngle, mWidth / 2, mHeight / 2);
        mPath.reset();
        mPath.moveTo(mP1.x, mP1.y);
        mPath.cubicTo(mP1.right.x, mP1.right.y, mP2.bottom.x, mP2.bottom.y, mP2.x, mP2.y);
        mPath.cubicTo(mP2.top.x, mP2.top.y, mP3.right.x, mP3.right.y, mP3.x, mP3.y);
        mPath.cubicTo(mP3.left.x, mP3.left.y, mP4.top.x, mP4.top.y, mP4.x, mP4.y);
        mPath.cubicTo(mP4.bottom.x, mP4.bottom.y, mP1.left.x, mP1.left.y, mP1.x, mP1.y);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     *    Update bezier point's coordinate
     */
    public void updateBezierCircle(float value, float offsetY) {
        int maxAngle = 40;
        int destX = 450;
        float p4ParamX = 1.4f;
        float p4ParamY = 5.0f;
        float centerX = mWidth / 2 + (destX - mWidth / 2) * value;
        float centerY = mHeight / 2 - offsetY;
        float radius = mRadius;

        mAngle = maxAngle * value;

        mP1.x = centerX;
        mP1.y = centerY + radius;
        mP1.left.x = centerX - mOffset;
        mP1.left.y = centerY + radius;
        mP1.right.x = centerX + mOffset;
        mP1.right.y = centerY + radius;

        mP3.x = centerX;
        mP3.y = centerY - radius;
        mP3.left.x = centerX - mOffset;
        mP3.left.y = centerY - radius;
        mP3.right.x = centerX + mOffset;
        mP3.right.y = centerY - radius;

        mP2.x = centerX + radius;
        mP2.y = centerY;
        mP2.top.x = centerX + radius;
        mP2.top.y = centerY - mOffset;
        mP2.bottom.x = centerX + radius;
        mP2.bottom.y = centerY + mOffset;

        mP4.x = centerX - radius - p4ParamX * radius * value;
        mP4.y = centerY;
        mP4.top.x = centerX - radius - p4ParamX * radius * value;
        mP4.top.y = centerY - mOffset - p4ParamY * value;
        mP4.bottom.x = centerX - radius - p4ParamX * radius * value;
        mP4.bottom.y = centerY + mOffset + p4ParamY * value;
    }

    /**
     *     Vertical direction bezier point
     */
    private class VPoint {
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();
    }

    /**
     *    horizontal direction bezier point
     */
    private class HPoint {
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();
    }
}
