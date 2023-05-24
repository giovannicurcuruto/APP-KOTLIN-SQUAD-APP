package com.example.case1squadapps.data.model.videoDevices

import com.example.case1squadapps.data.model.commonDevices.CommonDeviceResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoDeviceResponse(
    @SerializedName("data")
    val data: List<VideoDeviceModel>
) : Serializable
