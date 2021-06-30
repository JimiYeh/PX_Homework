package com.cloudinteractive.repository

import com.cloudinteractive.network.Client

class PxRepository {

    suspend fun getBanners() = Client.pxApiService.getBanners()

    suspend fun getMessages() = Client.pxApiService.getMessages()

    suspend fun getQRCode() = Client.pxApiService.getQRCode()
}