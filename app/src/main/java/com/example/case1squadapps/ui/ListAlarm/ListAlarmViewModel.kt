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
    //@ApplicationContext private val context: Context
):ViewModel() {

    private val _listAlarmCentrals = MutableStateFlow<ResourceState<AlarmCentralsResponse>>(ResourceState.Loading())
    var listAlarmCentrals: StateFlow<ResourceState<AlarmCentralsResponse>> = _listAlarmCentrals
    init { fetch() }

    private fun fetch() = viewModelScope.launch(){
        safeFetch()
    }


    private fun HandleAlarmsResponse(response: Response<AlarmCentralsResponse>): ResourceState<AlarmCentralsResponse> {
        if(response.isSuccessful){
            response.body()?.let { value ->
                return ResourceState.Success(value)
            }
        }
        return ResourceState.Error(response.message())
    }

    private suspend fun safeFetch() {
        try{
            val response = repositoryApp.getAllAlarmCentrals()
            _listAlarmCentrals.value = HandleAlarmsResponse(response)
        }catch (e:Throwable){
            when(e){
                is IOException -> _listAlarmCentrals.value = ResourceState.Error("Erro com a internet")
                else -> _listAlarmCentrals.value = ResourceState.Error("Falha na conversão de dados")
            }

        }
    }



}
