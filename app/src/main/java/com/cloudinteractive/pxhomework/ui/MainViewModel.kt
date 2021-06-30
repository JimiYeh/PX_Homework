package com.cloudinteractive.pxhomework.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinteractive.pxhomework.network.ApiResponse
import com.cloudinteractive.pxhomework.model.BaseResponse
import com.cloudinteractive.pxhomework.model.GetMessagesResult
import com.cloudinteractive.pxhomework.repository.PxRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val pxRepository by lazy { PxRepository() }

    private val _messages = MutableLiveData<List<GetMessagesResult.Message>>()
    val messages: LiveData<List<GetMessagesResult.Message>>
        get() = _messages

    fun getMessages() {
        viewModelScope.launch {
            when (val resp = pxRepository.getMessages()) {
                is ApiResponse.ApiSuccess<BaseResponse<GetMessagesResult>> -> {
                    if (resp.data.isSuccess())
                        _messages.value = resp.data.result.messages
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


    fun deleteMessages(deleteMessages: List<GetMessagesResult.Message>) {
        if (!deleteMessages.isEmpty())
            _messages.value = _messages.value?.filter {
                !deleteMessages.contains(it)
            }
    }
}