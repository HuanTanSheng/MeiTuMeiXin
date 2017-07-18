package com.huantansheng.meitumeixin.listener

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * Created by huan on 2017/7/18.
 */
class OnDragTouchListener(var cxt: Context, var view: View, var minDistance: Int, var listener: DragDirectionListener) : View.OnTouchListener {


    val dragGestureDetector = GestureDetector(cxt, DragGestureDetector())
    var totalDistanceX = 0
    var totalDistanceY = 0


    interface DragDirectionListener {
        fun left()
        fun right()
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        dragGestureDetector.onTouchEvent(p1)
        if (MotionEvent.ACTION_UP == p1!!.action) {
            if (Math.abs(totalDistanceX) < minDistance) {
                view.scrollBy(-totalDistanceX, -totalDistanceY)
            } else {
                if (totalDistanceX > 0) {
                    listener.left()
                } else {
                    listener.right()
                }
                view.scrollBy(-totalDistanceX, -totalDistanceY)

            }
            totalDistanceX = 0
            totalDistanceY = 0
        }

        return true
    }

    inner class DragGestureDetector : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            var x = distanceX.toInt()
            var y = distanceY.toInt()

            totalDistanceX += x
            totalDistanceY += y

            view.scrollBy(x, y)
            return true
        }
    }
}