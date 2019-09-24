package com.jdagnogo.blueground.mars.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdagnogo.blueground.mars.model.MarsUnit

class MarsUnitsAdapter() :
    PagedListAdapter<MarsUnit, RecyclerView.ViewHolder>(MarsUnitsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MarsUnitsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MarsUnitsViewHolder).bind(getItem(position))
    }
}