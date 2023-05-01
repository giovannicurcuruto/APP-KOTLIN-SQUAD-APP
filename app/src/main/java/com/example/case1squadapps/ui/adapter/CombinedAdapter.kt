package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.alarmCentrals.AlarmCentralsModel
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.databinding.ItemDeviceAlarmBinding
import com.example.case1squadapps.databinding.ItemDeviceBinding
import kotlinx.coroutines.*

class CombinedAdapter() : RecyclerView.Adapter<CombinedAdapter.CombinedViewHolder>() {

    inner class CombinedViewHolder(val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)

    private lateinit var allNames: List<String>

    private val differCallbackA = object: DiffUtil.ItemCallback<AlarmCentralsModel>(){
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


    private val differCallbackV = object: DiffUtil.ItemCallback<VideoDeviceModel>(){
        override fun areItemsTheSame(
            oldItem: VideoDeviceModel,
            newItem: VideoDeviceModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: VideoDeviceModel,
            newItem: VideoDeviceModel
        ): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.serial ==newItem.serial &&
                    oldItem.password == newItem.password && oldItem.username == newItem.username
        }
    }

    private val differA = AsyncListDiffer(this, differCallbackA)
    private val differV = AsyncListDiffer(this, differCallbackV)

    var videoDevices: List<VideoDeviceModel>
        get() = differV.currentList
        set(value) {
            differV.submitList(value)
            updateAllNames()
        }

    var alarmsDevices: List<AlarmCentralsModel>
        get() = differA.currentList
        set(value) {
            differA.submitList(value)
            updateAllNames()
        }

    private fun updateAllNames() {
        allNames = videoDevices.map { it.name } + alarmsDevices.map { it.name }
    }

    override fun onBindViewHolder(holder: CombinedViewHolder, position: Int){
        val name = allNames[position]
        holder.binding.apply {
            itemDevice.text = name
        }

        holder.binding.overFlowItemDevice.setOnClickListener {
            showPopupMenu(holder.binding.overFlowItemDevice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombinedViewHolder {
        return CombinedViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun getItemCount(): Int {
        return alarmsDevices.size + videoDevices.size
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

    private var onItemClickListenerAlarm: ((AlarmCentralsModel) -> Unit)? = null

    fun setOnClickListenerAlarm(listener: (AlarmCentralsModel) -> Unit){
        onItemClickListenerAlarm = listener
    }


    private var onItemClickListenerVideo: ((VideoDeviceModel) -> Unit)? = null

    fun setOnClickListenerVideo(listener: (VideoDeviceModel) -> Unit){
        onItemClickListenerVideo = listener
    }





}

/*

    private val differCallbackV = object: DiffUtil.ItemCallback<VideoDeviceModel>(){
        override fun areItemsTheSame(
            oldItem: VideoDeviceModel,
            newItem: VideoDeviceModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: VideoDeviceModel,
            newItem: VideoDeviceModel
        ): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.serial ==newItem.serial &&
                    oldItem.password == newItem.password && oldItem.username == newItem.username
        }

    }


    private val differCallbackA = object: DiffUtil.ItemCallback<AlarmCentralsModel>(){
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


    private val differV = AsyncListDiffer(this, differCallbackV)
    private val differA = AsyncListDiffer(this, differCallbackA)


    var alarmsDevices: List<AlarmCentralsModel>
        get() = differA.currentList
        set(value) = differA.submitList(value)

    var videoDevices: List<VideoDeviceModel>
        get() = differV.currentList
        set(value) = differV.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombinedViewHolder {
        return CombinedViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CombinedViewHolder, position: Int) {
        val alarm = alarmsDevices[position]
        //val video = videoDevices[position]

        holder.binding.apply {
            itemDevice.text = alarm.name.toString()
            println("############## sizeVideo" + videoDevices.size)


//            itemDevice.text = video.name.toString()

        }






//        val video = videoDevices[position]

/*
        holder.binding.apply {
            itemDevice.text = alarm.name.toString()


//            itemDevice.text = video.name.toString()

        }

 */

        holder.binding.overFlowItemDevice.setOnClickListener {
            showPopupMenu(holder.binding.overFlowItemDevice)
        }

        holder.itemView.setOnClickListener {
            onItemClickListenerAlarm?.let {
                it(alarm)
            }
        }


    }

    override fun getItemCount(): Int {
        return maxOf(alarmsDevices.size, videoDevices.size)

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

    private var onItemClickListenerAlarm: ((AlarmCentralsModel) -> Unit)? = null

    fun setOnClickListenerAlarm(listener: (AlarmCentralsModel) -> Unit){
        onItemClickListenerAlarm = listener
    }


    private var onItemClickListenerVideo: ((VideoDeviceModel) -> Unit)? = null

    fun setOnClickListenerVideol(listener: (VideoDeviceModel) -> Unit){
        onItemClickListenerVideo = listener
    }
 */