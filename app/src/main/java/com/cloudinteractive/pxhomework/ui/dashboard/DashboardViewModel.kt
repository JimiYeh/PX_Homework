package com.cloudinteractive.pxhomework.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinteractive.network.ApiResponse
import com.cloudinteractive.pxhomework.model.BaseResponse
import com.cloudinteractive.pxhomework.model.GetBannersResult
import com.cloudinteractive.pxhomework.ui.ToastMessageManager
import com.cloudinteractive.repository.PxRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val pxRepository by lazy { PxRepository() }

    private val _banners = MutableLiveData<List<GetBannersResult.Banner>>()
    val banners: LiveData<List<GetBannersResult.Banner>>
        get() = _banners

    fun getBanner() {
        viewModelScope.launch {
            when (val resp = pxRepository.getBanners()) {
                is ApiResponse.ApiSuccess<BaseResponse<GetBannersResult>> -> {
                    if (resp.data.isSuccess()) {
                        _banners.value = resp.data.result.banners
                    } else {
                        // TODO: 2021/6/30
                        // 如果知道 api 失敗時的 result 格式，
                        // 可以在 retrofit 加上 converter 轉出對應的 class 做處理
                        // ex: 取得 error message 用 toast 呈現
                    }
                }

                is ApiResponse.ApiError -> {
                    ToastMessageManager.emit("${resp.httpCode} error")
                }

                is ApiResponse.ApiException -> {
                    ToastMessageManager.emit("${resp.exception}")
                }
            }
        }
    }
}