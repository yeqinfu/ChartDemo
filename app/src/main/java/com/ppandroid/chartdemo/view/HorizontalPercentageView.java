package com.ppandroid.chartdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.ppandroid.chartdemo.graphical.view.GraphicalView;

/**
 * Created by yeqinfu on 2017/8/7.
 */

public class HorizontalPercentageView extends GraphicalView implements Runnable {



    public HorizontalPercentageView(Context context) {
        super(context);
        init();
    }

    public HorizontalPercentageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalPercentageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private int colorId=Color.RED;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    private void init() {
        strokeSize =20;
        setResultHeight((int) strokeSize*2);
    }

    private float strokeSize;
    private float percentage=0.5f;
    private float currentPercentage=percentage;

    public float getCurrentPercentage() {
        return currentPercentage;
    }

    public void setCurrentPercentage(float currentPercentage) {
        this.currentPercentage = currentPercentage;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    private Paint getPaint() {
        if (!isInEditMode()) {
            return new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            return new Paint();
        }
    }
    private Paint buildPaintForValue() {
        Paint paint = getPaint();
        paint.setColor(colorId);
        paint.setAlpha(88);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeSize);
        return paint;
    }


    @Override
    public void render(Canvas canvas) {

        //bg
        Paint p=buildPaintForValue();
        canvas.drawLine(strokeSize,strokeSize,getWidth()-strokeSize,strokeSize,p);
        p.setAlpha(255);
        //value
        canvas.drawLine(strokeSize,strokeSize,(getWidth()-strokeSize)*currentPercentage,strokeSize,p);
        //split
        Paint split=new Paint();
        split.setColor(Color.WHITE);
        split.setStrokeWidth(strokeSize);
        float start=strokeSize+getWidth()/3;
        float offset=(getWidth()-strokeSize)*currentPercentage-start;
        if (offset>0f){
            canvas.drawLine(start,strokeSize,start+Math.min(offset,1f),strokeSize,split);
            start=strokeSize+getWidth()/3*2;
            offset=(getWidth()-strokeSize)*currentPercentage-start;
            if (offset>0f){
                canvas.drawLine(start,strokeSize,start+Math.min(offset,1f),strokeSize,split);
            }
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
                setCurrentPercentage((float) i/100);
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
