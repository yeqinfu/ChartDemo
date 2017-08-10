package com.ppandroid.chartdemo.view;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppandroid.chartdemo.R;

/**
 * Created by yeqinfu on 2017/8/8.
 */

public class HeadViewLayout extends RelativeLayout {
    public HeadViewLayout(Context context) {
        super(context);
    }

    public HeadViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    // Content View Elements

    private TextView tv_back;
    private TextView tv_center;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_center = (TextView) findViewById(R.id.tv_center);
        tv_back.setText(backText);
        tv_center.setText(centerText);
    }
    public void init(final Activity activity){
        if (activity==null){
            return;
        }
        tv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }
    public void setBackText(String msg){
        if (tv_back!=null&& !TextUtils.isEmpty(msg)){
            tv_back.setText(msg);
        }
    }
    public void setCenterTitle(String msg){
        if (tv_center!=null&& !TextUtils.isEmpty(msg)){
            tv_center.setText(msg);
        }
    }
    private String backText="";
    private String centerText="";
    private int theme=0;


}
