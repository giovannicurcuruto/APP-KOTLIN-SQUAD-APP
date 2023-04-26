package com.example.case1squadapps.data.model.videoDevices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoDeviceModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String

): Serializable
