package com.cloudinteractive.pxhomework.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

object ToastMessageManager {

    private val messageFlow = MutableStateFlow<String?>(null)

    suspend fun emit(message: String) = messageFlow.emit(message)

    suspend fun collect(callback: (String?) -> Unit) {
        messageFlow.collect {
            callback.invoke(it)

            // clear message
            if (it != null)
                messageFlow.emit(null)
        }
    }
}