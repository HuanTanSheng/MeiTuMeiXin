package com.huantansheng.meitumeixin.controller

import android.app.Activity
import com.huantansheng.meitumeixin.main.MainActivity
import org.jetbrains.anko.startActivity

/**
 * Created by huan on 2017/7/8.
 */
object StartController {

    fun startMain(cxt: Activity) {
        cxt.startActivity<MainActivity>()
        cxt.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        cxt.finish()
    }
}