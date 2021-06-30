package com.cloudinteractive.pxhomework.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetMessagesResult(
    @SerializedName("messages")
    val messages: List<Message> = listOf()
) : Parcelable {
    @Parcelize
    data class Message(
        @SerializedName("msg")
        val msg: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("ts")
        val ts: String = ""
    ) : Parcelable
}