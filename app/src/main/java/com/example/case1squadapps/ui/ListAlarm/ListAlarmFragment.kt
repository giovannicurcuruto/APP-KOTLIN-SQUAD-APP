package com.example.case1squadapps.ui.ListAlarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.case1squadapps.databinding.FragmentListalarmBinding
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAlarmFragment :BaseFragment<FragmentListalarmBinding, ListAlarmViewModel>(){
    override val viewModel: ListAlarmViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListalarmBinding = FragmentListalarmBinding.inflate(inflater, container, false)
}