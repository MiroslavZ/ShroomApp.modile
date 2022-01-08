package com.example.shroomapp.presentation.farm_sensors.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.shroomapp.domain.Sensor

object SensorDiffCallback : DiffUtil.ItemCallback<Sensor>() {
    override fun areItemsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
        return oldItem == newItem
    }
}