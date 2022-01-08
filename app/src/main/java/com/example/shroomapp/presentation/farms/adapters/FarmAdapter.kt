package com.example.shroomapp.presentation.farms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shroomapp.databinding.CellFarmBinding
import com.example.shroomapp.domain.Farm

class FarmAdapter() :
    ListAdapter<Farm, FarmAdapter.FarmViewHolder>(FarmDiffCallback) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        val binding = CellFarmBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FarmViewHolder, position: Int) {
        val farm = getItem(position)
        with(holder) {
            binding.farmTitle.text = farm.name
            binding.farmSensorsCount.text = "датчиков: 7"
            binding.farmStatus.text = "WORKING"

            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(farm)
            }
        }
    }

    class FarmViewHolder(val binding: CellFarmBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(farm: Farm)
    }
}