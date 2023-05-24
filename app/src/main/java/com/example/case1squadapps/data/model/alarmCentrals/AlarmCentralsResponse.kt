package com.example.case1squadapps.data.model.alarmCentrals

import com.example.case1squadapps.data.model.commonDevices.CommonDeviceResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlarmCentralsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: MutableList<AlarmCentralsModel>
) : Serializable