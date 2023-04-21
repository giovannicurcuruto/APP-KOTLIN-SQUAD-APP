package com.example.case1squadapps.data.model.videoDevices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoDeviceResponse(
    @SerializedName("data")
    val data: List<VideoDeviceModel>
) : Serializable
