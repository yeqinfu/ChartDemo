package com.ppandroid.chartdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ppandroid.chartdemo.R;

/**
 * Created by yeqinfu on 2017/8/14.
 */

public class WaveView extends View {
    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        return paint;
    }

    /**控制点*/
    float contrY=0;
    private float speed=20;
    private boolean flag=true;
    float dataX=0;


    @Override
    protected void onDraw(Canvas canvas) {
        if (flag){
            contrY+=speed;
            dataX+=speed;
        }else{
            contrY-=speed;
            dataX-=speed;
        }
        if (contrY>=getHeight()){
            flag=false;
        }
        if (contrY<=-getHeight()/2){
            flag=true;
        }
        Path p=new Path();
        p.moveTo(-getWidth()/2f+dataX,getHeight()/2);
        p.quadTo(-getWidth()/4,contrY,0+dataX,getHeight()/2);
        p.quadTo(getWidth()/4,getHeight()-contrY,getWidth()/2+dataX,getHeight()/2);
        p.quadTo(getWidth()*3/4,contrY,getWidth()+dataX,getHeight()/2);
        p.quadTo(getWidth()*5/4,getHeight()-contrY,getWidth()*3/2+dataX,getHeight()/2);
        p.lineTo(getWidth()*3/2+dataX,getHeight());
        p.lineTo(-getWidth()/2f+dataX,getHeight());
        p.close();
        canvas.drawPath(p,getPathPaint());
      //  invalidate();

    }
}
