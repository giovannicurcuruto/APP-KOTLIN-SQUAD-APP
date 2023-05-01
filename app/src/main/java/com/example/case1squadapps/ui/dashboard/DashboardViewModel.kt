package com.example.case1squadapps.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.CommonModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceResponse
import com.example.case1squadapps.repository.Repository
import com.example.case1squadapps.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repositoryApp: Repository
): ViewModel() {

    private val _listAllVideo = MutableStateFlow<ResourceState<VideoDeviceResponse>>(ResourceState.Loading())
    val listAllVideo: StateFlow<ResourceState<VideoDeviceResponse>> = _listAllVideo

    private val _listAllAlarms = MutableStateFlow<ResourceState<AlarmCentralsResponse>>(ResourceState.Loading())
    val listAllAlarms: StateFlow<ResourceState<AlarmCentralsResponse>> = _listAllAlarms

    val combinedList: StateFlow<Pair<ResourceState<VideoDeviceResponse>, ResourceState<AlarmCentralsResponse>>> = combine(
        listAllVideo,
        listAllAlarms
    ) { videoResponse, alarmResponse ->
        Pair(videoResponse, alarmResponse)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Pair(ResourceState.Loading(), ResourceState.Loading())
    )

    init {
        fetch()

    }

    private fun fetch() = viewModelScope.launch{
        safeFetch()
    }

    private fun handleResponseAlarm(responseA: Response<AlarmCentralsResponse>): ResourceState<AlarmCentralsResponse> {
        if(responseA.isSuccessful){
            println("############################# HANDLERESPONSEALARM")
            responseA.body()?.let{values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(responseA.message())
    }
    private fun handleResponseVideo(responseV: Response<VideoDeviceResponse>): ResourceState<VideoDeviceResponse> {
        if(responseV.isSuccessful){
            println("############################# HANDLERESPONSEVIDEO")
            responseV.body()?.let{values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(responseV.message())
    }

    private suspend fun safeFetch() {
        try{
            val responseA = repositoryApp.getAllAlarmCentrals()
            val responseV = repositoryApp.listDVideo()
            _listAllVideo.value = handleResponseVideo(responseV)
            _listAllAlarms.value = handleResponseAlarm(responseA)

        } catch (e: Throwable){
            when(e){
                is IOException -> _listAllVideo.value = ResourceState.Error("Erro com a internet")
                else -> _listAllVideo.value = ResourceState.Error("Falha na conversão de dados")
            }
        }
    }


}