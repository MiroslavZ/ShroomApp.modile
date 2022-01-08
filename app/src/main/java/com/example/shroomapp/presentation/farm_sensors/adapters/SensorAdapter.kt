package com.example.shroomapp.presentation.farm_sensors.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shroomapp.R
import com.example.shroomapp.databinding.CellSensorBinding
import com.example.shroomapp.domain.Farm
import com.example.shroomapp.domain.Sensor
import com.example.shroomapp.presentation.farms.adapters.FarmAdapter

class SensorAdapter():
    ListAdapter<Sensor, SensorAdapter.SensorViewHolder>(SensorDiffCallback) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val binding= CellSensorBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return SensorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = getItem(position)
        with(holder){
            binding.sensorTitle.text = sensor.name
            binding.sensorValue.text = sensor.currentValue.toString()

            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(sensor)
            }
        }
    }

    class SensorViewHolder(val binding: CellSensorBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(sensor: Sensor)
    }
}