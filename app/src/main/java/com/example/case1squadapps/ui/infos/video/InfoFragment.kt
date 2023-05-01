package com.example.case1squadapps.ui.infos.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.case1squadapps.databinding.FragmentInfoVideoBinding
import com.example.case1squadapps.state.ResourceState
import com.example.case1squadapps.ui.adapter.VideoAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoFragment: BaseFragment<FragmentInfoVideoBinding, InfoViewModel>() {
    override val viewModel: InfoViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInfoVideoBinding = FragmentInfoVideoBinding.inflate(inflater, container, false)


}