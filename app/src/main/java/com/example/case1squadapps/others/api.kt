package com.example.case1squadapps.others

import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface api {

   @GET("alarm-centrals")
   suspend fun listAlarmDevices(
       @Query("id") id_alarm: String? = null
   ): Response<AlarmCentralsResponse>

    @GET("video-devices")
    suspend fun listVideoDevices(
        @Query("id") id_vd: String? = null
    ): Response<VideoDeviceResponse>


}