package com.example.case1squadapps.ui.ListAlarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsResponse
import com.example.case1squadapps.databinding.FragmentListalarmBinding
import com.example.case1squadapps.databinding.FragmentListvideoBinding
import com.example.case1squadapps.others.hide
import com.example.case1squadapps.others.show
import com.example.case1squadapps.others.toast
import com.example.case1squadapps.state.ResourceState
import com.example.case1squadapps.ui.ListVideo.ListVideoViewModel
import com.example.case1squadapps.ui.adapter.AlarmAdapter
import com.example.case1squadapps.ui.adapter.VideoAdapter
import com.example.case1squadapps.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ListAlarmFragment : Fragment(R.layout.fragment_listalarm), AlarmAdapter.OnItemClickListener{
    val viewModel: ListAlarmViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentListalarmBinding.bind(view)
        val alarmAdapter = AlarmAdapter(this)

        binding.apply{
            recyclerListAlarm.apply {
                adapter = alarmAdapter
                setHasFixedSize(true)

            }
        }

        viewModel.listAlarmCentrals.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceState.Success-> {
                    binding.progressCircular.hide()
                    it.data?.let {
                        alarmAdapter.submitList(it.data.toList())
                    }

                }
                is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    it.message?.let{ message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                        Timber.tag("ListAlarmFragment").e("Erro -> $message")
                    }

                }
                is ResourceState.Loading -> {
                    binding.progressCircular.show()
                }
                else -> {}
            }
        }
    }
    override fun onItemClick(alarmCentrals: AlarmCentralsModel) {
        //val action = ListAlarmFragmentDirections.actionListAlarmFragmentToDashboardFragment(alarmCentrals)
        //findNavController().navigate(action)
    }



}


