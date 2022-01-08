package com.example.shroomapp.presentation.farm_notifications.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shroomapp.databinding.CellNotificationBinding
import com.example.shroomapp.domain.Notification

class NotificationAdapter():
    ListAdapter<Notification, NotificationAdapter.NotificationViewHolder>(NotificationDiffCallback) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding= CellNotificationBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = getItem(position)
        with(holder){
            binding.sensorTitle.text = notification.sensorName
            binding.threshold.text = notification.threshold.toString()
            binding.condition.text = notification.condition.value

            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(notification)
            }
        }
    }

    class NotificationViewHolder(val binding:CellNotificationBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(notification: Notification)
    }
}