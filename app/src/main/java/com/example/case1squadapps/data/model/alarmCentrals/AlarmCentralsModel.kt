package com.example.case1squadapps.data.model.alarmCentrals

import android.net.MacAddress
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.case1squadapps.data.model.commonDevices.CommonDeviceModel

import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "alarmModel")
data class AlarmCentralsModel(
    @PrimaryKey(autoGenerate = true)
    val id_room: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("password")
    val password: Int
): Serializable
