package com.cloudinteractive.pxhomework.repository

import com.cloudinteractive.pxhomework.network.Client

class PxRepository {

    suspend fun getBanners() = Client.pxApiService.getBanners()

    suspend fun getMessages() = Client.pxApiService.getMessages()

    suspend fun getQRCode() = Client.pxApiService.getQRCode()
}