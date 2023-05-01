package com.example.case1squadapps.ui.infos.alarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.repository.Repository
import com.example.case1squadapps.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repositoryApp: Repository
): ViewModel(){

    private val _details = MutableStateFlow<ResourceState<AlarmCentralsResponse>>(ResourceState.Loading())
    val details: StateFlow<ResourceState<AlarmCentralsResponse>> = _details

    fun fetch(alarmId: String) = viewModelScope.launch(){
        safeFetch(alarmId)
    }

    private fun handleResponse(response: Response<AlarmCentralsResponse>): ResourceState<AlarmCentralsResponse> {
        if(response.isSuccessful){
            response.body()?.let {values->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    private suspend fun safeFetch(alarmId: String) {
        _details.value = ResourceState.Loading()
        try{
            val response = repositoryApp.getAlarmCentralsById(alarmId)
            _details.value = handleResponse(response)
        }catch (e: Throwable){
            when(e){
                is IOException -> _details.value = ResourceState.Error("Erro de Rede")
                else -> _details.value = ResourceState.Error("Erro na conversão")
            }
        }
    }

}