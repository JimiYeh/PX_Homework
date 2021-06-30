package com.cloudinteractive.pxhomework.network

import com.cloudinteractive.pxhomework.model.BaseResponse
import com.cloudinteractive.pxhomework.model.GetBannersResult
import com.cloudinteractive.pxhomework.model.GetMessagesResult
import com.cloudinteractive.pxhomework.model.GetQRCodeResult
import retrofit2.http.GET

interface PXApiService {

    @GET("/v3/f6733f2d-82fc-43e7-b19d-d8381f0ab91e")
    suspend fun getBanners(): ApiResponse<BaseResponse<GetBannersResult>>

    @GET("/v3/0f0488e1-e532-45e5-8033-bef5904359fe")
    suspend fun getMessages(): ApiResponse<BaseResponse<GetMessagesResult>>

    @GET("/v3/8c29aeec-3ab4-4ac1-9b2e-e99652dbd155")
    suspend fun getQRCode(): ApiResponse<BaseResponse<GetQRCodeResult>>
}