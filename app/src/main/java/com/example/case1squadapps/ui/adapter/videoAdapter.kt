package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.databinding.FragmentFabBinding
import com.example.case1squadapps.databinding.ItemDeviceBinding

class videoAdapter: RecyclerView.Adapter<videoAdapter.VideoDevicesViewHolder>() {

    inner class VideoDevicesViewHolder (val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<VideoDeviceModel>(){
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
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.macAddress ==newItem.macAddress && oldItem.password == newItem.password
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var vDevice: List<VideoDeviceModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoDevicesViewHolder {
        return VideoDevicesViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = vDevice.size

    override fun onBindViewHolder(holder: VideoDevicesViewHolder, position: Int) {
        val videoDevice = vDevice[position]
        holder.binding.apply {
            itemDevice.text = videoDevice.name
            itemIconDevice.text = videoDevice.id.toString()
        }
    }

    private var onItemClickListener: ((VideoDeviceModel) -> Unit)? = null

    fun setOnClickListener(listener: (VideoDeviceModel) -> Unit){
        onItemClickListener = listener
    }
}