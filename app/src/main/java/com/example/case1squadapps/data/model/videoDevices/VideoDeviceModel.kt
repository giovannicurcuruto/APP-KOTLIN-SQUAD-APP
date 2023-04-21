package com.example.case1squadapps.data.model.videoDevices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoDeviceModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("macAdress")
    val macAddress: String,
    @SerializedName("password")
    val password: Int

): Serializable
