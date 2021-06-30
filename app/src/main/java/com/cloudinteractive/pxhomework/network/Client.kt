package com.cloudinteractive.pxhomework.network

import android.os.Build
import com.cloudinteractive.pxhomework.BuildConfig

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {

    val pxApiService: PXApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("app_version", "${Build.VERSION.RELEASE}_${BuildConfig.VERSION_NAME}")
                    .url(it.request().url())
                it.proceed(requestBuilder.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()



        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        pxApiService = retrofit.create(PXApiService::class.java)
    }


}