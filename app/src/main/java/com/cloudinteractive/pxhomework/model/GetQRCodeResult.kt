package com.cloudinteractive.pxhomework.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetQRCodeResult(
    @SerializedName("qr_code")
    val qrCode: String = ""
) : Parcelable