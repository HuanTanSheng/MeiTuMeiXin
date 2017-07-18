package com.huantansheng.meitumeixin.base

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.huantansheng.meitumeixin.R

/**
 * Created by huan on 2017/7/14.
 */
object MyGlide {
    val requestOptions = RequestOptions()

    init {
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.abc_ic_ab_back_material)
                .placeholder(R.drawable.abc_scrubber_control_off_mtrl_alpha)
    }

    fun getOptions(): RequestOptions {
        return requestOptions
    }
}