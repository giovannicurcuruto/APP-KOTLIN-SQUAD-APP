package com.example.case1squadapps.ui.ListAlarm


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.others.NetworkUtil.Companion.hasInternetConnection
import com.example.case1squadapps.repository.Repository
import com.example.case1squadapps.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

import javax.inject.Inject


@HiltViewModel
class ListAlarmViewModel @Inject constructor(
    private val repositoryApp: Repository,
    @ApplicationContext private val context: Context
):ViewModel() {

    val listAlarmCentrals: MutableLiveData<ResourceState<AlarmCentralsResponse>> = MutableLiveData()
    var listAlarmResponse: AlarmCentralsResponse? = null
    init { fetch() }

    private fun fetch() = viewModelScope.launch(){
        safeFetch()
    }

    private fun handleResponse(response: Response<AlarmCentralsResponse>): ResourceState<AlarmCentralsResponse> {
        if(response.isSuccessful){
            response.body()?.let{ values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    private fun HandleAlarmsResponse(response: Response<AlarmCentralsResponse>): ResourceState<AlarmCentralsResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                if(listAlarmResponse == null)
                    listAlarmResponse = resultResponse
                else{
                    val oldItem = listAlarmResponse?.data
                    val newItem = resultResponse.data
                    oldItem?.addAll(newItem)
                }
                return ResourceState.Success(listAlarmResponse?: resultResponse)
            }
        }
        return ResourceState.Error(response.message())
    }

    private suspend fun safeFetch() {
        listAlarmCentrals.postValue(ResourceState.Loading())
        try{
            if(hasInternetConnection(context)) {
                println("################# repositoryApp.getAllAlarmCentrals()" + repositoryApp.getAllAlarmCentrals())
                val response = repositoryApp.getAllAlarmCentrals()

                listAlarmCentrals.postValue(HandleAlarmsResponse(response))
            }else{
                listAlarmCentrals.postValue(ResourceState.Error("Sem internet"))
            }
        }catch (e: java.lang.Exception){
            when(e){
                is IOException -> listAlarmCentrals.postValue(ResourceState.Error("Network Fail"))
                else -> listAlarmCentrals.postValue(ResourceState.Error("Erro de Conversão"))
            }
        }
    }



}
