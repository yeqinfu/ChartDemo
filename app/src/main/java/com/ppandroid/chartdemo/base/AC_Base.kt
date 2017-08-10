package com.ppandroid.im.base


import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup

/**
 * Created by Administrator on 2017/3/13. 所有通用页面的基类
 */

open class AC_Base : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onResume() {
        super.onResume()


    }


    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }


    companion object {
        fun getContentView(context: Activity): View {
            return (context.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        }
    }
}
