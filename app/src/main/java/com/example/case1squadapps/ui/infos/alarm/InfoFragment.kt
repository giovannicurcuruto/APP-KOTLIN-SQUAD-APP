package com.example.case1squadapps.ui.infos.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.databinding.FragmentInfoAlarmBinding
import com.example.case1squadapps.others.toast
import com.example.case1squadapps.state.ResourceState
import com.example.case1squadapps.ui.ListAlarm.ListAlarmFragmentDirections
import com.example.case1squadapps.ui.adapter.AlarmAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class InfoFragment: BaseFragment<FragmentInfoAlarmBinding, InfoViewModel>() {
    override val viewModel: InfoViewModel by viewModels()

    private val alarmAdapter by lazy { AlarmAdapter() }
    //private lateinit var alarmCentralsModel: AlarmCentralsModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.fetch("1f169779-63c8-4bcc-9219-a1ba75c58f81")
        clickDirection()
        observer()

    }

    private fun observer() = lifecycleScope.launch(){
        viewModel.details.collect{resource->
            when(resource){
                is ResourceState.Success ->{
                    resource.data?.let{values ->
                        alarmAdapter.alarmsDevices = values.data.toList()
                    }
                }
                is ResourceState.Error ->{
                    resource.message?.let{
                        toast(getString((R.string.an_error_occurred)))
                        Timber.tag("InfoFragment").e("Error -> erro")
                    }
                }
                else -> {}
            }

        }

    }

    private fun clickDirection() {
        alarmAdapter.setOnClickListener { alarmCentralsModel ->
            val action = ListAlarmFragmentDirections.actionListAlarmFragmentToInfoFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupView() = with(binding){

    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInfoAlarmBinding = FragmentInfoAlarmBinding.inflate(inflater, container, false)
}