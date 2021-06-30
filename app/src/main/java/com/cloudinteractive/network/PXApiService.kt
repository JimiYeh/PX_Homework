package com.cloudinteractive.network

import com.cloudinteractive.pxhomework.model.BaseResponse
import com.cloudinteractive.pxhomework.model.GetBannerResult
import retrofit2.http.GET

interface PXApiService {

    @GET("/v3/f6733f2d-82fc-43e7-b19d-d8381f0ab91e")
    suspend fun getBanners(): ApiResponse<BaseResponse<GetBannerResult>>
}