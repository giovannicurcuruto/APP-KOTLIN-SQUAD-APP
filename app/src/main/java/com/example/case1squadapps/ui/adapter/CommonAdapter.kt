package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.data.model.commonDevices.CommonDeviceModel
import com.example.case1squadapps.databinding.ItemDeviceBinding

class CommonAdapter(): RecyclerView.Adapter<CommonAdapter.CommonDevicesViewHolder>() {

    inner class CommonDevicesViewHolder(val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<CommonDeviceModel>(){
        override fun areItemsTheSame(oldItem: CommonDeviceModel, newItem: CommonDeviceModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CommonDeviceModel, newItem: CommonDeviceModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.macAddress == newItem.macAddress &&
                    oldItem.password == newItem.password &&
                    oldItem.userName == newItem.userName &&
                    oldItem.serial == newItem.serial
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var commonDevices: List<CommonDeviceModel>
        get() = differ.currentList
        set(value)= differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDevicesViewHolder {
        return CommonDevicesViewHolder(
            ItemDeviceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = commonDevices.size

    override fun onBindViewHolder(holder: CommonDevicesViewHolder, position: Int) {
        val devices = commonDevices[position]
        //println("############## $"+commonDevices.size)
        //println("#######################" + devices)
        //println("####################### ule" + commonDevices)

        holder.binding.apply {
            itemDevice.text = devices.name.toString()
        }
    }
}