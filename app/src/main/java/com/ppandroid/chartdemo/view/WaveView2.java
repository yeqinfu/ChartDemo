package com.ppandroid.chartdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ppandroid.chartdemo.R;

/**
 * Created by yeqinfu on 2017/8/14.
 */

public class WaveView2 extends View {



    public WaveView2(Context context) {
        super(context);
    }

    public WaveView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }
    private Paint getPathPaint() {
        Paint paint = getPaint();
        paint.setColor(getResources().getColor(R.color.chart_value_3));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5f);
        paint.setAlpha(88);
        return paint;
    }
    private Paint getPathPaint2() {
        Paint paint = getPaint();
        paint.setColor(getResources().getColor(R.color.chart_value_3));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5f);
        return paint;
    }
    private float[] mYPositions;
    private float[] mYPositions2;
    private static  float STRETCH_FACTOR_A = 1;
    private static  float mCycleFactorW = 1;
    private static  float OFFSET_Y = 0;
    private float b=0;
    private float b2=0;
    private float speed=30;
    private float speed2=15;

    @Override
    protected void onDraw(Canvas canvas) {
        long startTime = System.currentTimeMillis();
        mYPositions=new float[getWidth()];
        mYPositions2=new float[getWidth()];
        b=b+speed;
        b=b%getWidth();

        b2=b2+speed2;
        b2=b2%getWidth();
        Path p=new Path();
        Path p2=new Path();

        // 根据view总宽度得出所有对应的y值
        for (int i = 0; i < getWidth(); i++) {
            mYPositions[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * (i+b)) + OFFSET_Y);
            mYPositions2[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * (i+b2)) + OFFSET_Y);
            if (p.isEmpty()){
                p.moveTo(i,mYPositions[i]);
            }else{
                p.lineTo(i,mYPositions[i]);
            }
            if (p2.isEmpty()){
                p2.moveTo(i,mYPositions2[i]);
            }else{
                p2.lineTo(i,mYPositions2[i]);
            }
        }
        p.lineTo(getWidth(),getHeight());
        p.lineTo(0,getHeight());
        p.close();

        p2.lineTo(getWidth(),getHeight());
        p2.lineTo(0,getHeight());
        p2.close();
        canvas.drawPath(p2,getPathPaint2());
        canvas.drawPath(p,getPathPaint());


        long estimatedTime = System.currentTimeMillis() - startTime;
        Log.d("yeqinfu","+++++++++"+estimatedTime);

        invalidate();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / w);
        STRETCH_FACTOR_A=h/3-10;
        OFFSET_Y=h/2;
    }
}
