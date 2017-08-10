package com.ppandroid.app.home

import com.ppandroid.chartdemo.R
import com.ppandroid.chartdemo.bean.BN_Vertical
import com.ppandroid.chartdemo.view.whellview.FitChartHalf
import com.ppandroid.chartdemo.view.whellview.FitChartValue
import com.ppandroid.im.base.FG_Base
import kotlinx.android.synthetic.main.fg_over_view.*
import kotlinx.android.synthetic.main.yellowchartview.*
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by yeqinfu on 2017/8/3.
 */
class FG_OverView :FG_Base(){
    override fun fgRes():Int= R.layout.fg_over_view

    override fun afterViews() {
        var list=ArrayList<BN_Vertical>()
        for (i in 0..23){
            var bn= BN_Vertical()
            if (i%2==0){
                if (i<10){
                    bn.bottomText="0"+i
                }else{
                    bn.bottomText=""+i
                }

            }
            bn.totalHeight=100f
            var r=Random()
            bn.realHeight=r.nextFloat()*100f
            list.add(bn)
        }

        v_multiple_view.dataSet=list
        btn_multi.setOnClickListener {
            v_multiple_view.startAnim()

        }

        val fitChart = view?.find<FitChartHalf>(R.id.fitChart)
        fitChart?.let {
            it.minValue=0f
            it.maxValue=100f
        }
        btn_start_anim.setOnClickListener {
            v_yellow_chart.startAnim()
        }
        add.setOnClickListener {
            val resources = resources
            val values = ArrayList<FitChartValue>()
            values.add(FitChartValue(40f, resources.getColor(R.color.chart_value_1)))
            fitChart?.setValues(values)
        }
        btn_start.setOnClickListener {
            v_dount_view.startAnim()
        }
        btn_hp.setOnClickListener {
            v_hp.startAnim()
        }
    }

}