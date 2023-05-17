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


/*       @PrimaryKey(autoGenerate = true)
    val id_room: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("password")
    val password: Int



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
*/