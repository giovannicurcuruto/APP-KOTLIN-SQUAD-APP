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
import com.example.case1squadapps.ui.adapter.CommonAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment: BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    override val viewModel: DashboardViewModel by viewModels()

    private val commonAdapter by lazy { CommonAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.listAllDevices.collect{result->
            when(result){
                is ResourceState.Success ->{
                    binding.progressCircular.hide()
                    result.data?.let{sus->
                        commonAdapter.commonDevices = sus.data.toList()
                    }
                }
                is ResourceState.Error ->{
                    binding.progressCircular.hide()
                    result.message?.let { messageError->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("DashBoardFragment").e("Error -> $messageError")
                    }

                }
                is ResourceState.Loading ->{
                    binding.progressCircular.show()

                }
                else -> { }
            }
        }
    }



    private fun setupRecyclerView() = with(binding){
        recyclerDashboard.apply{
            adapter = commonAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)


}