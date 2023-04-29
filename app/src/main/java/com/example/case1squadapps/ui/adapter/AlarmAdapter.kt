package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.databinding.ItemDeviceAlarmBinding


class AlarmAdapter (
    private val listener: OnItemClickListener
    ): ListAdapter<AlarmCentralsModel, AlarmAdapter.AlarmViewHolder>(DiffCallback()) {


    inner class AlarmViewHolder(private val binding: ItemDeviceAlarmBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.apply {
                root.setOnClickListener{
                    val position = adapterPosition
                    if(position!= RecyclerView.NO_POSITION){
                        val alarmList = getItem(position)
                        listener.onItemClick(alarmList)
                    }
                }
            }
        }

        fun bind (alarmCentrals: AlarmCentralsModel){
            binding.apply {
                itemDeviceAlarm.text = alarmCentrals.name
                itemIconDeviceAlarm.text = alarmCentrals.id.toString()
            }
        }
    }


    interface OnItemClickListener{
        fun onItemClick(alarmCentrals: AlarmCentralsModel)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding = ItemDeviceAlarmBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val currencyItem = getItem(position)
        holder.bind(currencyItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<AlarmCentralsModel>() {
        override fun areItemsTheSame(
            oldItem: AlarmCentralsModel,
            newItem: AlarmCentralsModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: AlarmCentralsModel,
            newItem: AlarmCentralsModel
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.password == newItem.password &&
                    oldItem.macAddress == newItem.macAddress
        }

    }


}


