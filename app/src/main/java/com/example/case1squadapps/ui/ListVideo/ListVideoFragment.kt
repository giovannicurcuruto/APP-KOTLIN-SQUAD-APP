package com.example.case1squadapps.ui.ListVideo

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.databinding.FragmentListalarmBinding.inflate
import com.example.case1squadapps.databinding.FragmentListvideoBinding
import com.example.case1squadapps.others.Constants.DEFAULT_QUERY
import com.example.case1squadapps.others.Constants.LAST_SEARCH_QUERY
import com.example.case1squadapps.others.hide
import com.example.case1squadapps.others.show
import com.example.case1squadapps.others.toast
import com.example.case1squadapps.state.ResourceState
import com.example.case1squadapps.ui.adapter.VideoAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ListVideoFragment: BaseFragment<FragmentListvideoBinding, ListVideoViewModel>() {

    override val viewModel: ListVideoViewModel by viewModels()

    private val videoAdapter by lazy { VideoAdapter() }
    private lateinit var videoDeviceModel: VideoDeviceModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickAdapter()
        collectObserver()


    }

    private fun clickAdapter() {
        videoAdapter.setOnClickListener { videoDeviceModel ->
        val action = ListVideoFragmentDirections.actionListVideoFragmentToDashboardFragment(videoDeviceModel)
          findNavController().navigate(action)
        }
    }

    private fun collectObserver() = lifecycleScope.launch(){
        viewModel.listDeviceVideo.collect {resource ->
            when(resource){
                is ResourceState.Success -> {
                    binding.progressCircular.hide()
                    resource.data?.let{values->
                        videoAdapter.vDevice = values.data.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    resource.message?.let{message->
                        toast(getString((R.string.an_error_occurred)))
                        Timber.tag("ListVideoFragment").e("############################Error -> $message")
                    }
                }
                is ResourceState.Loading ->{
                    binding.progressCircular.show()
                }
                else ->{  }
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

