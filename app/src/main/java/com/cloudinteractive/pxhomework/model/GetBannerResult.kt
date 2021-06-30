package com.cloudinteractive.pxhomework.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetBannerResult(

    @SerializedName("banners")
    val banners: List<Banner> = listOf()
) : Parcelable {

    @Parcelize
    data class Banner(
        @SerializedName("image")
        val image: String = "",
        @SerializedName("target_url")
        val targetUrl: String = "",
        @SerializedName("title")
        val title: String = ""
    ) : Parcelable
}