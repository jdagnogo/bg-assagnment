package com.jdagnogo.blueground.mars.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jdagnogo.blueground.mars.model.MarsUnit

class MarsUnitsComparator :  DiffUtil.ItemCallback<MarsUnit>(){
    override fun areItemsTheSame(oldItem: MarsUnit, marsUnit: MarsUnit): Boolean {
        return oldItem.id == marsUnit.id
    }

    override fun areContentsTheSame(oldItem: MarsUnit, marsUnit: MarsUnit): Boolean {
        return oldItem == marsUnit
    }
}