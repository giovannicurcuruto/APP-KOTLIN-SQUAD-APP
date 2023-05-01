package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.databinding.ItemDeviceBinding

class InfoAdapter: RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {


    inner class InfoViewHolder(val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<AlarmCentralsModel>(){
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
            return oldItem.id == newItem.id && oldItem.name == newItem.name &&
                    oldItem.password == newItem.password && oldItem.macAddress == newItem.macAddress
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var alarmsDevices: List<AlarmCentralsModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val alarm = alarmsDevices[position]
        println("###########"+ alarmsDevices[position])
    }

    override fun getItemCount(): Int = alarmsDevices.size

    /**
    override fun onBindViewHolder(holder: AlarmAdapter.AlarmCentralsViewHolder, position: Int) {
        val alarm = alarmsDevices[position]
        holder.binding.apply {
            itemDevice.text = alarm.name.toString()
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(alarm)
            }
        }
    }
    */
    private var onItemClickListener: ((AlarmCentralsModel) -> Unit)? = null

    fun setOnClickListener(listener: (AlarmCentralsModel) -> Unit){
        onItemClickListener = listener
    }


}