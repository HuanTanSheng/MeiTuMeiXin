package com.huantansheng.meitumeixin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.huantansheng.meitumeixin.controller.StartController
import kotlinx.android.synthetic.main.activity_launch.*
import java.util.*

class LaunchActivity : AppCompatActivity() {

    val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        initView()
        initEvent()
    }

    fun initEvent() {
        val task = object : TimerTask() {
            override fun run() {
                toUIThread()
            }
        }

        timer.schedule(task, 2000)

    }

    fun toUIThread() {
        StartController.startMain(this@LaunchActivity)

    }

    private fun initView() {
        launchView.setBackgroundResource(R.mipmap.im_launch)
    }

}
