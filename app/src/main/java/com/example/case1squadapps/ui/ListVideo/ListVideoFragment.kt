package com.example.case1squadapps.ui.ListVideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.databinding.FragmentListvideoBinding
import com.example.case1squadapps.others.toast
import com.example.case1squadapps.state.ResourceState
import com.example.case1squadapps.ui.adapter.VideoAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ListVideoFragment: BaseFragment<FragmentListvideoBinding, ListVideoViewModel>() {

    override val viewModel: ListVideoViewModel by viewModels()

    private val videoAdapter by lazy { VideoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("################## 1")
        setupRecyclerView()
        println("################## 2")
        clickAdapter()
        collectObserver()
        print("################## 3")
    }

    private fun clickAdapter() {
        videoAdapter.setOnClickListener { videoDeviceModel ->
        //val action = ListVideoFragmentDirections            .actionListVideoFragmentToDashboardFragment(videoDeviceModel)

          //  findNavController().navigate(action)
        }
    }

    private fun collectObserver() = lifecycleScope.launch(){
        println("################## 3.1")
        viewModel.listDeviceVideo.collect {resource ->
            println("################## 3.2")
            when(resource){
                is ResourceState.Success -> {
                    resource.data?.let{values->
                        videoAdapter.vDevice = values.data.toList()
                    }
                }
                is ResourceState.Error -> {
                    resource.message?.let{message->
                        toast(getString((R.string.an_error_occurred)))
                        Timber.tag("ListVideoFragment").e("############################Error -> $message")
                    }
                }
                else ->{

                }
            }

        }
    }


    private fun setupRecyclerView() = with(binding){
        recyclerListVideo.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListvideoBinding = FragmentListvideoBinding.inflate(inflater, container, false)
}

/*
*
*
*
*
*
*
*
* */