package com.huantansheng.meitumeixin.controller

import android.support.v7.widget.CardView
import com.huantansheng.meitumeixin.constants.Constants
import com.huantansheng.meitumeixin.utiles.ScreenUtil

/**
 * Created by huan on 2017/7/8.
 */
object ViewController {

    fun setCardViewParams(screenWidth: Int, spanCount: Int, card: CardView, imageWidth: Int, imageHeight: Int) {
        val offsetDP = (spanCount - 1) * 8 + 32
        val offsetPX = ScreenUtil.dp2px(card.context, offsetDP.toFloat())
        val width = (screenWidth - (offsetPX)) / spanCount
        val height = ((width / imageWidth.toFloat()) * imageHeight).toInt()

        var layoutParams = card.layoutParams
        layoutParams.width = width
        layoutParams.height = height

        card.layoutParams = layoutParams
    }
}