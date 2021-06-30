package com.cloudinteractive.pxhomework.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BaseResponse<T: Parcelable>(
    @SerializedName("status_code")
    val statusCode: Int = -1,

    @SerializedName("result")
    val result: T,
) {
    fun isSuccess() = statusCode == 0
}