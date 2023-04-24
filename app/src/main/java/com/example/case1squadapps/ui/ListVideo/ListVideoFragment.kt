package com.example.case1squadapps.ui.ListVideo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.case1squadapps.databinding.FragmentListvideoBinding
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListVideoFragment: BaseFragment<FragmentListvideoBinding, ListVideoViewModel>() {
    override val viewModel: ListVideoViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListvideoBinding = FragmentListvideoBinding.inflate(inflater, container, false)
}