package com.example.shroomapp.presentation.farms.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.shroomapp.domain.Farm

object FarmDiffCallback: DiffUtil.ItemCallback<Farm>() {
    override fun areItemsTheSame(oldItem: Farm, newItem: Farm): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Farm, newItem: Farm): Boolean {
        return oldItem == newItem
    }
}