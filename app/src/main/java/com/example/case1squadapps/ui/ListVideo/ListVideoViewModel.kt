package com.example.case1squadapps.ui.ListVideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceResponse
import com.example.case1squadapps.others.api
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
class ListVideoViewModel @Inject constructor(
    private val repository: Repository,
    private val api:api
): ViewModel() {

    private val _listDeviceVideo = MutableStateFlow<ResourceState<VideoDeviceResponse>>(ResourceState.Loading())
    val listDeviceVideo: StateFlow<ResourceState<VideoDeviceResponse>> = _listDeviceVideo


    init{
        fetch()
    }
    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        try{
            println("########### DEU RUIM SE APARECER AQUI HEIN")


            val response = repository.listDVideo()

            _listDeviceVideo.value = handleResponse(response)
        }catch (e: Throwable){
            when(e){
                is IOException -> _listDeviceVideo.value = ResourceState.Error("############Erro com a internet")
                else -> _listDeviceVideo.value = ResourceState.Error("############Falha na conversão de dados")
            }
        }
    }

    private fun handleResponse(response: Response<VideoDeviceResponse>): ResourceState<VideoDeviceResponse> {

        if(response.isSuccessful){
            response.body()?.let{values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error("###################### ERRO NO RESOURCE"+    response.message())
    }
}