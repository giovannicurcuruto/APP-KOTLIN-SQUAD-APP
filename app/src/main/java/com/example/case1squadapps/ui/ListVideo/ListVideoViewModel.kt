package com.example.case1squadapps.ui.ListVideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
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
class ListVideoViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {

    private val _listDeviceVideo = MutableStateFlow<ResourceState<VideoDeviceResponse>>(ResourceState.Loading())
    val listDeviceVideo: StateFlow<ResourceState<VideoDeviceResponse>> = _listDeviceVideo

    init{
        fetch(null)
    }
    fun fetch(id: String?) = viewModelScope.launch {
        safeFetch(id)
    }

    private suspend fun safeFetch(id: String?) {

        if(id.isNullOrBlank()){
            try{
                val response = repository.listDVideo()
                _listDeviceVideo.value = handleResponse(response)
            }catch (e: Throwable){
                when(e){
                    is IOException -> _listDeviceVideo.value = ResourceState.Error("Erro com a internet")
                    else -> _listDeviceVideo.value = ResourceState.Error("Falha na conversão de dados")
                }
            }
        }else{
            try{
                val response = repository.listDVideo(id)
                _listDeviceVideo.value = handleResponse(response)
            }catch (e: Throwable){
                when(e){
                    is IOException -> _listDeviceVideo.value = ResourceState.Error("Erro com a internet")
                    else -> _listDeviceVideo.value = ResourceState.Error("Falha na conversão de dados")
                }
            }
        }

    }

    private fun handleResponse(response: Response<VideoDeviceResponse>): ResourceState<VideoDeviceResponse> {
        if(response.isSuccessful){
            response.body()?.let{values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    fun insert(videoViewModel: VideoDeviceModel) = viewModelScope.launch{
        repository.insertVideo(videoViewModel)
    }
}