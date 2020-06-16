package com.BV.LinearGradient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;

public class LinearGradientView extends View {

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Path mPathForBorderRadius;
    private RectF mTempRectForBorderRadius;
    private LinearGradient mShader;

    private float[] mLocations;
    private float[] mStartPos = {0, 0};
    private float[] mEndPos = {0, 1};
    private int[] mColors;
    private boolean mUseAngle = false;
    private float[] mAngleCenter = new float[]{0.5f, 0.5f};
    private float mAngle = 45f;
    private int[] mSize = {0, 0};
    private float[] mBorderRadii = {0, 0, 0, 0, 0, 0, 0, 0};

    private int mTextColor = getResources().getColor(R.color.colorWhite);
    private int mTextSize = 15;
    private String mTextString = "";


    public LinearGradientView(Context context) {
        super(context);
    }

    public void setStartPosition(float[] startPos) {
        mStartPos = startPos;
        drawGradient();
    }

    public void setEndPosition(float[] endPos) {
        mEndPos = endPos;
        drawGradient();
    }

    public void setColors(int[] colors) {
        mColors = colors;
        drawGradient();
    }

    public void setLocations(float[] locations) {
        mLocations = locations;
        drawGradient();
    }

    public void setUseAngle(boolean useAngle) {
        mUseAngle = useAngle;
        drawGradient();
    }

    public void setAngleCenter(float[] in) {
        mAngleCenter = in;
        drawGradient();
    }

    public void setAngle(float angle) {
        mAngle = angle;
        drawGradient();
    }

    public void setBorderRadii(float[] borderRadii) {
        mBorderRadii = borderRadii;
        updatePath();
        drawGradient();
    }

    public void setBorderRadius(float radius){
        for (int i = 0 ;i <= mBorderRadii.length;i++){
            mBorderRadii[i] = radius;
        }
        updatePath();
        drawGradient();
    }

    public void setText(String text){
        mTextString = text;
        updatePath();
        drawGradient();
    }

    public void setTextSize(int v){
        mTextSize = v;
        updatePath();
        drawGradient();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mSize = new int[]{w, h};
        updatePath();
        drawGradient();
    }

    private float[] calculateGradientLocationWithAngle(float angle) {
        float angleRad = (angle - 90.0f) * ((float)Math.PI / 180.0f);
        float length = (float)Math.sqrt(2.0);

        return new float[]{
                (float) Math.cos(angleRad) * length,
                (float) Math.sin(angleRad) * length
        };
    }

    /**
     * 初始化文字paint
     */
    private void drawTextPaint() {
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        mTextPaint.setTypeface(font);
        mTextPaint.setTextSize(sp2px(mTextSize));
    }



    private void drawGradient() {
        // guard against crashes happening while multiple properties are updated
        if (mColors == null || (mLocations != null && mColors.length != mLocations.length))
            return;

        float[] startPos = mStartPos;
        float[] endPos = mEndPos;

        if (mUseAngle && mAngleCenter != null) {
            float[] angleSize = calculateGradientLocationWithAngle(mAngle);
            startPos = new float[]{
                    mAngleCenter[0] - angleSize[0] / 2.0f,
                    mAngleCenter[1] - angleSize[1] / 2.0f
            };
            endPos = new float[]{
                    mAngleCenter[0] + angleSize[0] / 2.0f,
                    mAngleCenter[1] + angleSize[1] / 2.0f
            };
        }

        mShader = new LinearGradient(
                startPos[0] * mSize[0],
                startPos[1] * mSize[1],
                endPos[0] * mSize[0],
                endPos[1] * mSize[1],
            mColors,
            mLocations,
            Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);

        drawTextPaint();

        invalidate();
    }

    private void updatePath() {
        if (mPathForBorderRadius == null) {
            mPathForBorderRadius = new Path();
            mTempRectForBorderRadius = new RectF();
        }
        mPathForBorderRadius.reset();
        mTempRectForBorderRadius.set(0f, 0f, (float) mSize[0], (float) mSize[1]);
        mPathForBorderRadius.addRoundRect(
            mTempRectForBorderRadius,
            mBorderRadii,
            Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPathForBorderRadius == null) {
            canvas.drawPaint(mPaint);
        } else {
            canvas.drawPath(mPathForBorderRadius, mPaint);
        }
        if (!TextUtils.isEmpty(mTextString)){
            drawText(canvas);
        }

    }



    private void drawText(Canvas canvas){
        RectF rectF = new RectF(mBorderRadii[0], mBorderRadii[0], mSize[0] - mBorderRadii[0], mSize[1] - mBorderRadii[0]);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (rectF.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText(mTextString, rectF.centerX(), baseLineY, mTextPaint);
    }


    private int dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
