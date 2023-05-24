package com.example.case1squadapps.data.model.videoDevices

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.case1squadapps.data.model.commonDevices.CommonDeviceModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "videoModel")
data class VideoDeviceModel(
    @PrimaryKey(autoGenerate = true)
    val id_room: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String

): Serializable
