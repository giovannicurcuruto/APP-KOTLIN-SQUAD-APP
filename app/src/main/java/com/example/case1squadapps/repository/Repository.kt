package com.example.case1squadapps.repository

import com.example.case1squadapps.data.local.DaoInterface
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceResponse
import com.example.case1squadapps.others.api
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: api,
    private val dao: DaoInterface
) {
    suspend fun listDVideo(id: String? = null): Response<VideoDeviceResponse>{
        if (id != null){
            return api.listVideoDevicesById(id)
        }else{
            return api.listVideoDevices()
        }
    }

    suspend fun listAlarm() = api.listAlarmDevices()

    suspend fun getAllAlarmCentrals(): Response<AlarmCentralsResponse> {
        return api.listAlarmDevices()
    }
    suspend fun listAlarmModel(id: String? = null) = api.listAlarmDevicesById(id)

    suspend fun insertVideo(videoDeviceModel: VideoDeviceModel) = dao.insert(videoDeviceModel)

    suspend fun insertAlarm(alarmCentralsModel: AlarmCentralsModel) = dao.insert(alarmCentralsModel)

    fun getAllVideo() = dao.getAllVideo()

    fun getAllAlarm() = dao.getAllAlarm()

    suspend fun delete(videoDeviceModel: VideoDeviceModel) = dao.delete(videoDeviceModel)

    suspend fun delete(alarmCentralsModel: AlarmCentralsModel) = dao.delete(alarmCentralsModel)




}