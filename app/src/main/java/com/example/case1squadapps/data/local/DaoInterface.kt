package com.example.case1squadapps.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoDeviceModel: VideoDeviceModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alarmCentralsModel: AlarmCentralsModel): Long

    @Query("SELECT * FROM videoModel ORDER BY id_room")
    fun getAllVideo(): Flow<List<VideoDeviceModel>>

    @Query("SELECT * FROM alarmModel ORDER BY id_room")
    fun getAllAlarm(): Flow<List<AlarmCentralsModel>>

    @Delete
    suspend fun delete(videoDeviceModel: VideoDeviceModel)

    @Delete
    suspend fun delete(alarmCentralsModel: AlarmCentralsModel)

}