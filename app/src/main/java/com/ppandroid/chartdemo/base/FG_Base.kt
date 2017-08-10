// (c)2016 Flipboard Inc, All Rights Reserved.

package com.ppandroid.im.base


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ppandroid.chartdemo.base.AC_ContentFG
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


/**
 * 功能性base 不放业务base逻辑
 */
abstract class FG_Base : Fragment() {
    protected abstract fun fgRes():Int
    protected abstract fun afterViews()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(fgRes(), container, false)
        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViews()
    }


    protected fun finish() {
        activity.finish()
    }

    protected fun startAC(fragment:String){
        var it= AC_ContentFG.createIntent(activity,fragment)
        startActivity(it)
    }


    /**
     * 将string 数据转换成网络请求格式
     */
    protected fun String2UI8(string: String): String {
        var string = string
        try {
            string = URLEncoder.encode(string, "utf-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return string
    }




    companion object {
        fun getContentView(context: Activity): View {
            return (context.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        }
    }
}
