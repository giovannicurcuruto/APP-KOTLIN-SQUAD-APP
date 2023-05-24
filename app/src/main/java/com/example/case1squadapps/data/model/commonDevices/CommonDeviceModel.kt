package com.example.case1squadapps.data.model.commonDevices

import com.google.gson.annotations.SerializedName
data class CommonDeviceModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("macAddress")
    val macAddress: String
): java.io.Serializable


/*

interface CommonDeviceModel {}
 */
