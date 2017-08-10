package com.ppandroid.chartdemo.view;

import java.util.ArrayList;
import java.util.List;

import com.ppandroid.chartdemo.bean.BN_Vertical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yeqinfu on 2017/8/10.
 * 多条树状图
 */

public class MultipleVerticalView extends View implements Runnable{
    public MultipleVerticalView(Context context) {
        super(context);
        initSetting();
    }

    public MultipleVerticalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    public MultipleVerticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }
    private void initSetting() {
        strokeSize=40f;

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
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
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(strokeSize);

        return paint;
    }


    private List<BN_Vertical> dataSet = new ArrayList<>();

    public List<BN_Vertical> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<BN_Vertical> dataSet) {
        this.dataSet = dataSet;

    }

    private float progress = 0.0f;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (!isInEditMode()){
            strokeSize=getWidth()/(dataSet.size()*2);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint p=getTextPaint();
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (bottom-top);//基线中间点的y轴计算公式

        for (int i=0;i<dataSet.size();i++) {
            BN_Vertical item=dataSet.get(i);
            float itemProgress=progress*item.getRealHeight()/item.getTotalHeight();
            float begin=(i+0.5f)*getWidth()/dataSet.size();
            float h=itemProgress*(getHeight()-baseLineY*2.5f);
            canvas.drawText(item.getTopText(),begin,getHeight()-baseLineY*1.5f-h,p);
            canvas.drawText(item.getBottomText(),begin,getHeight(),p);
            Paint p2=getPicPaint();
            float v1=getHeight()-h-baseLineY;
            float v2=getHeight()-baseLineY;
            if (v1<v2){
                canvas.drawLine(begin,v1,begin,v2,p2);
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
                setProgress((float) i/100);
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
