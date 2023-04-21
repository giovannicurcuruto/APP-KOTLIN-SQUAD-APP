package com.example.case1squadapps.data.model.alarmCentrals

import android.net.MacAddress
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlarmCentralsModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("password")
    val password: Int
): Serializable
