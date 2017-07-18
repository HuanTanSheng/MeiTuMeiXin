package com.huantansheng.meitumeixin.photo


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.huantansheng.meitumeixin.R
import com.huantansheng.meitumeixin.base.MyGlide
import com.huantansheng.meitumeixin.listener.OnDragTouchListener
import com.huantansheng.meitumeixin.utiles.ScreenUtil
import kotlinx.android.synthetic.main.activity_photo.*
import org.jetbrains.anko.toast

class PhotoActivity : AppCompatActivity(), OnDragTouchListener.DragDirectionListener {
    override fun left() {
        toast("left")
        showPhoto()
    }

    override fun right() {
        toast("right")
        showPhoto()
    }


    val TAG = "PhotoActivity"
    var photos = ArrayList<String>()
    var i = 0
    lateinit var requestManager: RequestManager
    lateinit var gestureDetector: GestureDetector
    val minDistance = ScreenUtil.dp2px(null, 80f)

    companion object {
        val INTENT_DATA = "list"
        val INTENT_POSITION = "position"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        init()
    }

    fun init() {
        photos.addAll(intent.getStringArrayListExtra(INTENT_DATA))
        i = intent.getIntExtra(INTENT_POSITION, 0)
        requestManager = Glide.with(this)
        requestManager.applyDefaultRequestOptions(MyGlide.getOptions())

        iv_photo.setOnTouchListener(OnDragTouchListener(this, iv_photo, minDistance, this))

        showPhoto()
    }

    fun showPhoto() {
        requestManager.load(photos[i]).into(iv_photo)
        i++
        if (i == photos.size) {
            i = 0
        }
        requestManager.load(photos[i]).into(iv_back_photo)
    }

}

