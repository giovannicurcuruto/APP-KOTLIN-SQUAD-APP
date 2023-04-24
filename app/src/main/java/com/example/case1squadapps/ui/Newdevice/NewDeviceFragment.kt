package com.example.case1squadapps.ui.Newdevice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.case1squadapps.databinding.FragmentFabBinding
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewDeviceFragment: BaseFragment<FragmentFabBinding, NewDeviceViewModel>(){
    override val viewModel: NewDeviceViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFabBinding = FragmentFabBinding.inflate(inflater, container, false)
}