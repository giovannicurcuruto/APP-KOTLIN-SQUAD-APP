package com.example.case1squadapps.data.model.alarmCentrals

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlarmCentralsResponse(
    @SerializedName("data")
    val data: List<AlarmCentralsModel>
): Serializable
