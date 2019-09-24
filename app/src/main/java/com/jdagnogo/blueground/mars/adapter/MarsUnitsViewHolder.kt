package com.jdagnogo.blueground.mars.adapter

import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.api.LoginServiceApi.Companion.ENDPOINT
import com.jdagnogo.blueground.mars.model.MarsUnit
import kotlinx.android.synthetic.main.list_item_unit.view.*

class MarsUnitsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): MarsUnitsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_unit, parent, false)
            return MarsUnitsViewHolder(view)
        }
        private const val CURRENCY = "€ "
    }


    fun bind(marsUnit: MarsUnit?) {
        marsUnit?.apply {
            itemView.title.text = title?:""
            itemView.region.text = region
            itemView.description.text = description
            itemView.price.text =CURRENCY + price
            itemView.ratingBar.numStars = rating.toInt()
            if (isBooked) {
                itemView.book_information.visibility = View.VISIBLE
            } else {
                itemView.book_information.visibility = View.GONE
            }
            val ratingBar = itemView.ratingBar
            val stars = ratingBar.progressDrawable as LayerDrawable
            stars.setTint(ContextCompat.getColor(ratingBar.context, R.color.colorAccent))
            val imageView = itemView.image
            if (!pictures[0].isEmpty()) {
                Glide.with(imageView.context)
                    .load(ENDPOINT + pictures[0])
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            }
        }
    }
}