package com.cloudinteractive.pxhomework.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinteractive.network.ApiResponse
import com.cloudinteractive.pxhomework.model.BaseResponse
import com.cloudinteractive.pxhomework.model.GetBannerResult
import com.cloudinteractive.repository.PxRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val pxRepository by lazy { PxRepository() }


}