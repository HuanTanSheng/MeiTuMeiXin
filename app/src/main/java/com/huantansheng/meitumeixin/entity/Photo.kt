package com.huantansheng.meitumeixin.entity

import java.io.Serializable

/**
 * Created by huan on 2017/7/18.
 */
data class Photo (val url: String, val width: Int, val height: Int, var isLiked: Boolean?):Serializable {
}