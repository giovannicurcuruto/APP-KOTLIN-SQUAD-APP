package com.example.case1squadapps.ui.adapter

import android.view.*
import android.widget.Button
import android.widget.PopupMenu
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.case1squadapps.R
import com.example.case1squadapps.data.model.videoDevices.VideoDeviceModel
import com.example.case1squadapps.databinding.ItemDeviceBinding
import com.example.case1squadapps.others.toast
import kotlin.coroutines.coroutineContext

class VideoAdapter: RecyclerView.Adapter<VideoAdapter.VideoDevicesViewHolder>() {

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
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.serial ==newItem.serial &&
                    oldItem.password == newItem.password && oldItem.username == newItem.username
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
            itemDevice.text = videoDevice.name.toString()

        }

        holder.binding.overFlowItemDevice.setOnClickListener {
            showPopupMenu(holder.binding.overFlowItemDevice)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(videoDevice)
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

    private var onItemClickListener: ((VideoDeviceModel) -> Unit)? = null

    fun setOnClickListener(listener: (VideoDeviceModel) -> Unit){
        onItemClickListener = listener
    }

}