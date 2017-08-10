package com.ppandroid.chartdemo.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yeqinfu on 2017/8/7.
 */

public class CheckView extends View implements Runnable {
    public CheckView(Context context) {
        super(context);
        init();
    }


    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }

    private Paint bgPaint = null;

    private Paint getBgPaint() {
        bgPaint = getPaint();
        bgPaint.setColor(Color.WHITE);
        bgPaint.setAlpha(88);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStrokeWidth(strokeSize);
        return bgPaint;
    }

    private Paint valuePaint = null;

    private Paint getValuePaint() {
        valuePaint = getPaint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setStyle(Paint.Style.STROKE);
        valuePaint.setStrokeCap(Paint.Cap.ROUND);
        valuePaint.setStrokeWidth(strokeSize);
        return valuePaint;
    }

    private float strokeSize;
    private float mRadius = 0.0f;
    private float cx = 0.0f;
    private float cy = 0.0f;
    static final int START_ANGLE = 270;

    private void init() {
        strokeSize = 5;
    }

    //动画进度
    private float progress = 0.0f;


    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(cx, cy, mRadius, getBgPaint());
        Path path = new Path();
        path.addArc(drawingArea, START_ANGLE, progress * 360);
        canvas.drawPath(path, getValuePaint());
        canvas.save();
        // 将坐标系原点移动到画布正中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        float xx = (float) Math.sin(progress * Math.PI * 2) * mRadius;
        float yy = (float) Math.cos(progress * Math.PI * 2) * mRadius;
        Paint p=getValuePaint();
        p.setAlpha(50);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(xx, -yy, 10, p);
        p.setAlpha(255);
        canvas.drawCircle(xx, -yy, 5, p);
        canvas.restore();
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int min = Math.min(w - getPaddingLeft() - getPaddingRight(), h - getPaddingTop() - getPaddingBottom());
        mRadius = min / 2;
        cx = w / 2;
        cy = h / 2;
        calculateDrawableArea();
    }

    private RectF drawingArea;

    /**
     * 计算绘制区域
     */
    private void calculateDrawableArea() {
        drawingArea = new RectF(-mRadius + cx, -mRadius + cy, mRadius + cx, mRadius + cy);
    }

    public void startAnim() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            chartAnimation();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private void chartAnimation() {
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(20);
                if (listener!=null){
                    listener.change((float) i / 100);
                }else{
                }
                setProgress((float) i / 100);
                postInvalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }
    }
    private  ProgreesListener listener;

    public ProgreesListener getListener() {
        return listener;
    }

    public void setListener(ProgreesListener listener) {
        this.listener = listener;
    }

    public interface ProgreesListener{
        void change(float progress);
    }
}
