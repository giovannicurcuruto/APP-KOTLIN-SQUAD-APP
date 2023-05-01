package com.example.case1squadapps.ui.infos.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceResponse
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
    private val repository: Repository
): ViewModel(){


    private val _details = MutableStateFlow<ResourceState<VideoDeviceResponse>>(ResourceState.Loading())
    val details: StateFlow<ResourceState<VideoDeviceResponse>> = _details

    fun fetch(VideoId: String) = viewModelScope.launch(){
        safeFetch(VideoId)
    }

    private fun handleResponse(response: Response<VideoDeviceResponse>): ResourceState<VideoDeviceResponse> {
        if(response.isSuccessful){
            response.body()?.let {values->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    private suspend fun safeFetch(videoId: String) {
        _details.value = ResourceState.Loading()
        try{
            val response = repository.listDVideo(videoId)
            _details.value = handleResponse(response)
        }catch (e: Throwable){
            when(e){
                is IOException -> _details.value = ResourceState.Error("Erro de Rede")
                else -> _details.value = ResourceState.Error("Erro na conversão")
            }
        }
    }
}