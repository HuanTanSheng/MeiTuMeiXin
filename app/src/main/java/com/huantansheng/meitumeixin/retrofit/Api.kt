package com.huantansheng.meitumeixin.retrofit

import com.huantansheng.meitumeixin.entity.Photos
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by huan on 2017/7/18.
 */
object Api {
    val httpLogger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val okClient = OkHttpClient.Builder()
            .addInterceptor(httpLogger)
            .build()

    val mRetrofit = Retrofit.Builder()
            .client(okClient)
            .baseUrl("https://raw.githubusercontent.com/HuanTanSheng/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val mService = mRetrofit.create(ApiService::class.java)

    fun getPhotos(): Observable<Photos> {
        return mService.getPhotos()
    }


}