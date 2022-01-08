package com.example.shroomapp.presentation.farm_notifications.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.shroomapp.domain.Notification

object NotificationDiffCallback : DiffUtil.ItemCallback<Notification>() {
    override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
        return oldItem == newItem
    }
}