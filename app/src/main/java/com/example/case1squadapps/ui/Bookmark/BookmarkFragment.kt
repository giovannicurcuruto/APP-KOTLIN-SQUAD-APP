package com.example.case1squadapps.ui.Bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.case1squadapps.databinding.FragmentBookmarkBinding
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment:BaseFragment<FragmentBookmarkBinding, BookmarkViewModel>() {
    override val viewModel: BookmarkViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater, container, false)
}