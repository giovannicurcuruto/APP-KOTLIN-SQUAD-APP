package com.example.case1squadapps.repository

import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.others.api
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: api
) {
    suspend fun listDVideoById(id: String? = null) = api.listVideoDevicesById(id)

    suspend fun listDVideo() = api.listVideoDevices()

    suspend fun listAlarm() = api.listAlarmDevices()

    //Testando para verificar esta função via conseguir pegar todos os dispositivos
    suspend fun getAllAlarmCentrals(): Response<AlarmCentralsResponse> {
        return api.listAlarmDevices()
    }
    suspend fun listAlarmModel(id: String? = null) = api.listAlarmDevicesById(id)

}