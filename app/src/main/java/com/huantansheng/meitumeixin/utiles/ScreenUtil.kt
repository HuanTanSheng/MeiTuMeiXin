package com.huantansheng.meitumeixin.utiles

import android.content.Context
import org.jetbrains.anko.displayMetrics

/**
 * Created by huan on 2017/7/14.
 */
object ScreenUtil {
    private var screenWidth = 0
    private var density = 0f
    fun getWidth(cxt: Context): Int {
        if (0 == screenWidth)
            screenWidth = cxt.displayMetrics.widthPixels
        return screenWidth
    }

    fun dp2px(cxt: Context?, dpValue: Float): Int {
        if (0f == density) {
            density = cxt!!.displayMetrics.density
        }
        return (dpValue * density + 0.5f).toInt()
    }
}