package com.example.case1squadapps.data.model.commonDevices

import com.google.gson.annotations.SerializedName



data class CommonDeviceResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("data")
    var data: List<CommonDeviceModel>
):java.io.Serializable


/*

interface CommonDeviceResponse { }
 */