package com.cloudinteractive.repository

import com.cloudinteractive.network.Client

class PxRepository {

    suspend fun getBanners() = Client.pxApiService.getBanners()
}