package com.example.case1squadapps.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case1squadapps.R
import com.example.case1squadapps.databinding.FragmentDashboardBinding
import com.example.case1squadapps.others.hide
import com.example.case1squadapps.others.show
import com.example.case1squadapps.others.toast
import com.example.case1squadapps.state.ResourceState
import com.example.case1squadapps.ui.adapter.AlarmAdapter
import com.example.case1squadapps.ui.adapter.CombinedAdapter
import com.example.case1squadapps.ui.adapter.VideoAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment: BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    override val viewModel: DashboardViewModel by viewModels()

    private val combinedAdapter by lazy { CombinedAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.combinedList.collect{(videoResources, alarmResources) ->
            when {
                videoResources is ResourceState.Success && alarmResources is ResourceState.Success-> {
                    binding.progressCircular.hide()
                    videoResources.data?.let{values->
                        alarmResources.data?.let { valuesA ->
                            //
                            combinedAdapter.alarmsDevices = valuesA.data.toList()
                            combinedAdapter.videoDevices = values.data.toList()
                            //println("#####" + combinedAdapter.videoDevices)
                            //println("#####" + combinedAdapter.alarmsDevices)
                        }
                    }
                }
                videoResources is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    videoResources.message?.let{message->
                        toast(getString((R.string.an_error_occurred)))
                        Timber.tag("DashboardFragment").e("Error -> $message")
                    }

                }
                alarmResources is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    videoResources.message?.let{message->
                        toast(getString((R.string.an_error_occurred)))
                        Timber.tag("DashboardFragment").e("Error -> $message")
                    }

                }
                videoResources is ResourceState.Loading && alarmResources is ResourceState.Loading->{
                    binding.progressCircular.show()
                }
                else ->{  }
            }
        }
    }



    private fun setupRecyclerView() = with(binding){
        recyclerDashboard.apply{
            adapter = combinedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)


}