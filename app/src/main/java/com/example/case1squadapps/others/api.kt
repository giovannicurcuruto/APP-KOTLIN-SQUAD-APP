package com.example.case1squadapps.others

import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.data.model.commonDevices.CommonDeviceResponse
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {

   @GET("alarm-centrals")
   suspend fun listAlarmDevices(): Response<AlarmCentralsResponse>

    @GET("alarm-centrals")
    suspend fun listAlarmDevicesCommon(): Response<CommonDeviceResponse>

    @GET("video-devices")
    suspend fun listVideoDevices(): Response<VideoDeviceResponse>

    @GET("video-devices")
    suspend fun listVideoDevicesCommon(): Response<CommonDeviceResponse>

    @GET("alarm-centrals")
    suspend fun listAlarmDevicesById(
        @Path(value = "id",
            encoded = true) id_video: String?
    ): Response<AlarmCentralsResponse>



    @GET("video-devices/{id_video}")
    suspend fun listVideoDevicesById(
        @Path(value = "id",
            encoded = true) id_video: String?
    ): Response<VideoDeviceResponse>



}