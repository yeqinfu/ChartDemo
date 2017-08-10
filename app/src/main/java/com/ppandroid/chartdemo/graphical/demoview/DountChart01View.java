/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package com.ppandroid.chartdemo.graphical.demoview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.ppandroid.chartdemo.graphical.chart.DountChart;
import com.ppandroid.chartdemo.graphical.chart.PieData;
import com.ppandroid.chartdemo.graphical.event.click.ArcPosition;
import com.ppandroid.chartdemo.graphical.render.XEnum;
import com.ppandroid.chartdemo.graphical.render.info.PlotLegend;
import com.ppandroid.chartdemo.graphical.view.DemoView;

import java.util.LinkedList;

/**
 * @ClassName DountChart01View
 * @Description 环形图例子
 * @author XiongChuanLiang<br/>
 * 		(xcl_168@aliyun.com)
 */
public class DountChart01View extends DemoView implements Runnable{

	private String		TAG			= "DountChart01View";
	private DountChart chart		= new DountChart();

	LinkedList<PieData>	lPieData	= new LinkedList<PieData>();

	public DountChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	public DountChart01View(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DountChart01View(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		chartDataSet();
		chartRender();

		//綁定手势滑动事件
		//	this.bindTouch(this,chart);
	}
	public void startAnim(){
        new Thread(this).start();
    }

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//图所占范围大小
		chart.setChartRange(w, h);
	}

	private void chartRender() {
		try {
			//设置绘图区默认缩进px值
			int[] ltrb = getPieDefaultSpadding();
			chart.setPadding(10, 10, 10, 10);
            chart.setInitialAngle(270);
            chart.setBackgroundColor(Color.WHITE);

			//数据源
			chart.setDataSource(lPieData);
		/*	chart.setCenterText("新品太多!!!");
			chart.getCenterTextPaint().setColor(Color.rgb(242, 167, 69));*/

			//标签显示(隐藏，显示在中间，显示在扇区外面) 
			chart.setLabelStyle(XEnum.SliceLabelStyle.HIDE);
			chart.getLabelPaint().setColor(Color.WHITE);

			//标题
			//chart.setTitle("环形图");
			//chart.addSubtitle("(XCL-Charts Demo)");
			//显示key
			//chart.getPlotLegend().show();		
			//显示图例
			PlotLegend legend = chart.getPlotLegend();
			legend.hide();

			//图背景色
			chart.setApplyBackgroundColor(false);
			chart.setBackgroundColor(Color.rgb(19, 163, 224));

			//内环背景色
			chart.getInnerPaint().setColor(Color.WHITE);

			//显示边框线，并设置其颜色
			chart.getArcBorderPaint().setColor(Color.YELLOW);

			//可用这个修改环所占比例
			//chart.setInnerRadius(0.6f);

			//chart.setInitialAngle(90.f);

			//设置附加信息
			//addAttrInfo();

			//保存标签位置
			chart.saveLabelsPosition(XEnum.LabelSaveType.ALL);

			//激活点击监听
			chart.ActiveListenItemClick();
			chart.showClikedFocus();

			chart.setInnerRadius(0.8f);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}

	private void addAttrInfo() {
		/////////////////////////////////////////////////////////////
		//设置附加信息
		Paint paintTB = new Paint();
		paintTB.setColor(Color.GRAY);
		paintTB.setTextAlign(Align.CENTER);
		paintTB.setTextSize(25);
		chart.getPlotAttrInfo().addAttributeInfo(XEnum.Location.TOP, "九月的手机,", 0.5f, paintTB);
		chart.getPlotAttrInfo().addAttributeInfo(XEnum.Location.BOTTOM, "绝对不够......", 0.5f, paintTB);

		Paint paintLR = new Paint();
		paintLR.setTextAlign(Align.CENTER);
		paintLR.setTextSize(25);
		paintLR.setColor(Color.rgb(191, 79, 75));
		chart.getPlotAttrInfo().addAttributeInfo(XEnum.Location.LEFT, "性能高!", 0.5f, paintLR);
		chart.getPlotAttrInfo().addAttributeInfo(XEnum.Location.RIGHT, "诱惑大!", 0.5f, paintLR);

		Paint paintBase = new Paint();
		paintBase.setTextAlign(Align.CENTER);
		paintBase.setTextSize(25);
		paintBase.setColor(Color.rgb(242, 167, 69));
		chart.getPlotAttrInfo().addAttributeInfo(XEnum.Location.BOTTOM, "一个肾,", 0.3f, paintBase);
		/////////////////////////////////////////////////////////////
	}

	private void chartDataSet() {
		//设置图表数据源				
		//PieData(标签，百分比，在饼图中对应的颜色) #F76D6D #FF8D25 #FFB33B
		lPieData.add(new PieData("Solaris", "90%", 89.4, Color.rgb(247, 109, 109)));
		lPieData.add(new PieData("split", "1%", 0.2, Color.WHITE));
		lPieData.add(new PieData("Aix", "7%", 7, Color.rgb(255, 141, 25)));
        lPieData.add(new PieData("split", "1%", 0.2, Color.WHITE));
		lPieData.add(new PieData("HP-UX", "3%", 3, Color.rgb(255, 179, 59)));
        lPieData.add(new PieData("split", "1%", 0.2, Color.WHITE));
	}

	@Override
	public void render(Canvas canvas) {
		try {
			chart.render(canvas);

			/*
			 * 
			 * 在显示标签的位置显示图片:
			 * 
			 1.chart.saveLabelsPosition(XEnum.LabelSaveType.ONLYPOSITION);
			 2. 返回各标签位置
			  
			*/

			/*
			 * 贴图的例子代码：
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pieaa);  
			
			ArrayList<PlotArcLabelInfo> mLstLabels = chart.getLabelsPosition();	    
			for(PlotArcLabelInfo info: mLstLabels)
			{
				PointF pos = info.getLabelPointF();
				if(null == pos)continue;
				//String posXY = " x="+Float.toString(pos.x)+" y="+Float.toString(pos.y);
				//Log.e("Pie","label="+lPieData.get(info.getID())+" "+posXY);	   
				
				canvas.drawBitmap(bmp, pos.x, pos.y, null); 
			}
			*/

		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
		super.onTouchEvent(event);
		/*	if(event.getAction() == MotionEvent.ACTION_UP)
			{						
				triggerClick(event.getX(),event.getY());
			}*/
		return true;
	}

	//触发监听
	private void triggerClick(float x, float y) {
		if (!chart.getListenItemClickStatus())
			return;
		ArcPosition record = chart.getPositionRecord(x, y);
		if (null == record)
			return;

		PieData pData = lPieData.get(record.getDataID());

		boolean isInvaldate = true;
		for (int i = 0; i < lPieData.size(); i++) {
			PieData cData = lPieData.get(i);
			if (i == record.getDataID()) {
				if (cData.getSelected()) {
					isInvaldate = false;
					break;
				}
				else {
					cData.setSelected(true);
				}
			}
			else
				cData.setSelected(false);
		}

		if (isInvaldate)
			this.invalidate();
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

    private void chartAnimation()
    {
        try {

            chart.setDataSource(lPieData);
            int count = 360 / 10;

            for(int i=1;i<count ;i++)
            {
                Thread.sleep(20);

                chart.setTotalAngle(10 * i);

                //激活点击监听
                if(count - 1 == i)
                {
                    chart.setTotalAngle(360);

                    chart.ActiveListenItemClick();
                    //显示边框线，并设置其颜色
                    chart.getArcBorderPaint().setColor(Color.YELLOW);
                    chart.getArcBorderPaint().setStrokeWidth(3);
                }

                postInvalidate();
            }

        }
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }

    }


}
