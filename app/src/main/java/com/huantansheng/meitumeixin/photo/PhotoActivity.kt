package com.huantansheng.meitumeixin.photo


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.huantansheng.meitumeixin.R
import com.huantansheng.meitumeixin.entity.Photo
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
    var photos = ArrayList<Photo>()
    var i = 0

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
        photos.addAll(intent.getSerializableExtra(INTENT_DATA) as ArrayList<Photo>)
        i = intent.getIntExtra(INTENT_POSITION, 0)
        iv_photo.setOnTouchListener(OnDragTouchListener(this, iv_photo, minDistance, this))

        showPhoto()
    }

    fun showPhoto() {
        Glide.with(this).load(photos[i].url).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_photo)
        i++
        if (i == photos.size) {
            i = 0
        }
        Glide.with(this).load(photos[i].url).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_back_photo)
    }

}

