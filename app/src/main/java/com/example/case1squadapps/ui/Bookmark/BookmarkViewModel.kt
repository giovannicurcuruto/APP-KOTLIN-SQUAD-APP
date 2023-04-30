package com.example.case1squadapps.ui.Bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.repository.Repository
import com.example.case1squadapps.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repositoryApp: Repository
): ViewModel() {
    private val _favoritesAlarm = MutableStateFlow<ResourceState<List<AlarmCentralsModel>>>(ResourceState.Empty())
    val favoritesAlarm: StateFlow<ResourceState<List<AlarmCentralsModel>>> = _favoritesAlarm

    private val _favoritesVideo = MutableStateFlow<ResourceState<List<VideoDeviceModel>>>(ResourceState.Empty())
    val favoritesVideo: StateFlow<ResourceState<List<VideoDeviceModel>>> = _favoritesVideo

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch{
        repositoryApp.getAllAlarm().collectLatest {
            if(it.isNullOrEmpty()){
                _favoritesAlarm.value = ResourceState.Empty()
            }else{
                _favoritesAlarm.value = ResourceState.Success(it)
            }
        }

        repositoryApp.getAllVideo().collectLatest {
            if(it.isNullOrEmpty()){
                _favoritesVideo.value = ResourceState.Empty()
            }else{
                _favoritesVideo.value = ResourceState.Success(it)
            }
        }
    }
    fun delete(alarmCentralsModel: AlarmCentralsModel) = viewModelScope.launch { repositoryApp.delete(alarmCentralsModel) }

    fun delete(videoDeviceModel: VideoDeviceModel) = viewModelScope.launch { repositoryApp.delete(videoDeviceModel) }
}