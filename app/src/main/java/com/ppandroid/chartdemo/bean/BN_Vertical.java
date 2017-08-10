package com.ppandroid.chartdemo.bean;

import android.graphics.Color;

/**
 * Created by yeqinfu on 2017/8/10.
 */

public class BN_Vertical {
	String	topText="";
	String	bottomText="";
	float	totalHeight=0.0f;
	float	realHeight=0.0f;
	int		lineColor	= Color.WHITE;

	public String getTopText() {
		return topText;
	}

	public void setTopText(String topText) {
		this.topText = topText;
	}

	public String getBottomText() {
		return bottomText;
	}

	public void setBottomText(String bottomText) {
		this.bottomText = bottomText;
	}

	public int getLineColor() {
		return lineColor;
	}

	public void setLineColor(int lineColor) {
		this.lineColor = lineColor;
	}

	public float getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(float totalHeight) {
		this.totalHeight = totalHeight;
	}

	public float getRealHeight() {
		return realHeight;
	}

	public void setRealHeight(float realHeight) {
		this.realHeight = realHeight;
	}
}
