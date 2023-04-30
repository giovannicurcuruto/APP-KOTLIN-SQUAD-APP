package com.example.case1squadapps.data.local

import androidx.room.RoomDatabase
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel

@androidx.room.Database(
    entities = [AlarmCentralsModel::class, VideoDeviceModel::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseDao : RoomDatabase() {
    abstract fun DBDao() : DaoInterface
}