package com.example.case1squadapps.repository

import com.example.case1squadapps.others.api
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: api
) {
    suspend fun listDVideoById(id: String? = null) = api.listVideoDevicesById(id)

    suspend fun listDVideo() = api.listVideoDevices()

}