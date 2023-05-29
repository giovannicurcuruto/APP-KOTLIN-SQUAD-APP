package com.example.case1squadapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.commonDevices.CommonDeviceModel
import com.example.case1squadapps.databinding.ItemDeviceBinding
import com.example.case1squadapps.ui.ListAlarm.ListAlarmFragmentDirections

class CommonAdapter(): RecyclerView.Adapter<CommonAdapter.CommonDevicesViewHolder>() {

    inner class CommonDevicesViewHolder(val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<CommonDeviceModel>(){
        override fun areItemsTheSame(oldItem: CommonDeviceModel, newItem: CommonDeviceModel): Boolean {
            return oldItem.id == newItem.id && oldItem.serial == newItem.serial && oldItem.name == newItem.name && oldItem.userName == newItem.userName && oldItem.password == newItem.password && oldItem.macAddress == newItem.macAddress
        }

        override fun areContentsTheSame(oldItem: CommonDeviceModel, newItem: CommonDeviceModel): Boolean {
            return oldItem.id == newItem.id && oldItem.serial == newItem.serial && oldItem.name == newItem.name && oldItem.userName == newItem.userName && oldItem.password == newItem.password && oldItem.macAddress == newItem.macAddress
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
        println("############## $"+commonDevices.size)
        println("#######################" + devices)
        println("####################### ule" + commonDevices)
        println("################### test" + devices.macAddress)

        holder.binding.apply {
            itemDevice.text = devices.name.toString()
            if(devices.macAddress != null){
                imageView.setImageResource(R.drawable.icalarmdevice)
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let{
                it(devices)
            }
        }

        holder.binding.overFlowItemDevice.setOnClickListener {
            showPopupMenu(holder.binding.overFlowItemDevice)
        }


    }

    private var onItemClickListener: ((CommonDeviceModel) -> Unit)? = null

    fun setOnClickListener(listener: (CommonDeviceModel) -> Unit){
        onItemClickListener = listener
    }

    private fun showPopupMenu(overFlowItemDevice: Button){
        val popup = PopupMenu(overFlowItemDevice.context, overFlowItemDevice)
        popup.inflate(R.menu.side_menu)
        popup.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.containerInfo -> {
                    val navController = Navigation.findNavController(overFlowItemDevice)
                    val action = ListAlarmFragmentDirections.actionListAlarmFragmentToInfoFragment()
                    navController.navigate(action)
                    true
                }
                else ->{
                    false
                }
            }
        }

        popup.show()

    }


}