package com.ppandroid.chartdemo.view;


import com.ppandroid.chartdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by yeqinfu on 2017/8/9.
 */

public class VerticalView extends View implements Runnable{
    public VerticalView(Context context) {
        super(context);
        initSetting();
    }

    public VerticalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        readAttributes(attrs);
        initSetting();
    }



    public VerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(attrs);
        initSetting();
    }

    private String topStr="";
    private String bottomStr="";
    private float totalHeight=0f;
    private float realHeight=0f;
    private float progress=0.0f;
    private int lineColor=Color.WHITE;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    /**
     * 属性读取
     * @param attrs
     */
    private void readAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext()
                    .getTheme().obtainStyledAttributes(attrs, R.styleable.verticalview, 0, 0);
            totalHeight = attributes
                    .getFloat(R.styleable.verticalview_totalHeight, 0f);
            realHeight = attributes
                    .getFloat(R.styleable.verticalview_realHeight, 0f);
            topStr = attributes
                    .getString(R.styleable.verticalview_topText);
            bottomStr = attributes
                    .getString(R.styleable.verticalview_bottomText);
            lineColor=attributes.getColor(R.styleable.verticalview_lineColor,Color.WHITE);
            attributes.recycle();
        }
    }

    private void initSetting() {
        strokeSize=40f;

    }
    private float strokeSize;
    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }
    private Paint getTextPaint() {
        Paint paint = getPaint();
        paint.setTextSize(30f);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }
    private Paint getPicPaint(){
        Paint paint = getPaint();
        paint.setColor(lineColor);
        paint.setStrokeWidth(strokeSize);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=getTextPaint();
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (bottom-top);//基线中间点的y轴计算公式


        float begin=getWidth()/2;
        float h=progress*(getHeight()-baseLineY*2.5f);
        canvas.drawText(topStr,begin,getHeight()-baseLineY*1.5f-h,p);
        canvas.drawText(bottomStr,begin,getHeight(),p);
        Paint p2=getPicPaint();


        float v1=getHeight()-h-baseLineY;
        float v2=getHeight()-baseLineY;
        if (v1<v2){
            canvas.drawLine(begin,v1,begin,v2,p2);
        }
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            chartAnimation();
        }
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    private void chartAnimation(){
        for (int i=1;i<=100;i++){
            try {
                Thread.sleep(5);
                float t=realHeight/totalHeight;
                setProgress((float) i*t/100);
                postInvalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

        }
    }
    public void startAnim(){
        new Thread(this).start();
    }
}
