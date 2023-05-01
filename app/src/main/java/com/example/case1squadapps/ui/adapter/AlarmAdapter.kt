package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.databinding.ItemDeviceAlarmBinding
import com.example.case1squadapps.databinding.ItemDeviceBinding


class AlarmAdapter (): RecyclerView.Adapter<AlarmAdapter.AlarmCentralsViewHolder>() {

    inner class AlarmCentralsViewHolder (val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmCentralsViewHolder {
        return AlarmCentralsViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = alarmsDevices.size

    override fun onBindViewHolder(holder: AlarmCentralsViewHolder, position: Int) {
        val alarm = alarmsDevices[position]
        holder.binding.apply {
            itemDevice.text = alarm.name.toString()

        }

        holder.binding.overFlowItemDevice.setOnClickListener {
            showPopupMenu(holder.binding.overFlowItemDevice)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(alarm)
            }
        }


    }

    private fun showPopupMenu(overFlowItemDevice: Button) {
        val popup = PopupMenu(overFlowItemDevice.context, overFlowItemDevice)
        popup.inflate(R.menu.side_menu)
        popup.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.opFavorite ->{
                    true
                }
                else -> { false}
            }
        }
        popup.show()
    }

    private var onItemClickListener: ((AlarmCentralsModel) -> Unit)? = null

    fun setOnClickListener(listener: (AlarmCentralsModel) -> Unit){
        onItemClickListener = listener
    }


}

/*
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
            }

            binding.overFlowItemDeviceAlarm.setOnClickListener {
                showPopupMenu(binding.overFlowItemDeviceAlarm)
            }
        }
    }

    private fun showPopupMenu(overFlowItemDeviceAlarm: Button) {

            val popup = PopupMenu(overFlowItemDeviceAlarm.context, overFlowItemDeviceAlarm)
            popup.inflate(R.menu.side_menu)
            popup.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.opFavorite ->{
                        true
                    }
                    else -> { false}
                }
            }
            popup.show()

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

 */
