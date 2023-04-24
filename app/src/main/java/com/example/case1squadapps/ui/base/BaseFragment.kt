package com.example.case1squadapps.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VBinding:ViewBinding, VModel: ViewModel>: Fragment() {

    private var _binding: VBinding? = null
    protected val binding get() = _binding!!
    protected abstract val viewModel: VModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VBinding

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}