package com.example.case1squadapps.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.data.model.commonDevices.CommonDeviceResponse
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

    private val _listAllDevices = MutableStateFlow<ResourceState<CommonDeviceResponse>>(ResourceState.Loading())
    val listAllDevices: StateFlow<ResourceState<CommonDeviceResponse>> = _listAllDevices

    /*

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

    */


    init {
        fetch()

    }

    private fun fetch() = viewModelScope.launch{
        safeFetch()
    }
/*
    private fun handleResponseAlarm(responseA: Response<AlarmCentralsResponse>): ResourceState<AlarmCentralsResponse> {
        if(responseA.isSuccessful){
            println("############################# HANDLERESPONSEALARM")
            responseA.body()?.let{values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(responseA.message())
    }

 */

    private suspend fun safeFetch() {
        try{
            val responseA = repositoryApp.getAllDevicesAlarm()
            val responseV = repositoryApp.getAllDevicesVideo()
            // println("################ responseA" + responseA)
            //rintln("################ responseV" + responseV.body()?.data)

            _listAllDevices.value = handleResponseCommonDevices(responseV, responseA)
            //println("#################### " + _listAllDevices.value)

        } catch (e: Throwable){
            when(e){
                is IOException -> _listAllDevices.value = ResourceState.Error("Erro com a internet")
                else -> _listAllDevices.value = ResourceState.Error("Falha na conversão de dados")
            }
        }
    }

    private fun handleResponseCommonDevices(
        responseV: Response<CommonDeviceResponse>,
        responseA: Response<CommonDeviceResponse>
    ): ResourceState<CommonDeviceResponse> {
        if(responseA.isSuccessful || responseV.isSuccessful){
            responseA.body()?.let {valuesA ->
                responseV.body()?.let {valuesV ->
                    //println("############# "+ valuesA)
                    //println("############# "+ valuesV)
                    valuesA.count = valuesA.count+valuesV.count
                    valuesA.data = valuesA.data+valuesV.data
                    //println("############# " + valuesA.count + valuesV.count)
                    //println("############# " + valuesA)
                    return ResourceState.Success(valuesA)
                }
            }
        }
        return ResourceState.Error(responseA.message())
    }


}