package com.huantansheng.meitumeixin.retrofit

import com.huantansheng.meitumeixin.entity.Photos
import retrofit2.http.GET
import rx.Observable

/**
 * Created by huan on 2017/7/18.
 */
interface ApiService {

    @GET("https://raw.githubusercontent.com/HuanTanSheng/MeiTuMeiXin/master/app/src/main/assets/photos.json")
    fun getPhotos(): Observable<Photos>

}