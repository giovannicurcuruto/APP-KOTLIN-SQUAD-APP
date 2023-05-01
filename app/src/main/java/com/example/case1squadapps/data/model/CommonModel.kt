package com.example.case1squadapps.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CommonModel(
    val id: String,
    val name: String,
    val serial: String,
    val username: String,
    val macAddress: String,
    val password: String
): java.io.Serializable
