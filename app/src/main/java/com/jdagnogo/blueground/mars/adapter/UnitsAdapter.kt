package com.jdagnogo.blueground.mars.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.model.MarsUnit
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.graphics.Color
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat


class UnitsAdapter : RecyclerView.Adapter<UnitsAdapter.UnitListItem>() {
    companion object {
        private const val CURRENCY = "€ "
    }

    lateinit var mContext: Context
    val mDataset = mutableListOf<MarsUnit>()

    class UnitListItem(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val region: TextView = view.findViewById(R.id.region)
        val description: TextView = view.findViewById(R.id.description)
        val price: TextView = view.findViewById(R.id.price)
        val rating: RatingBar = view.findViewById(R.id.ratingBar)
        val image: ImageView = view.findViewById(R.id.image)
        val card: CardView = view.findViewById(R.id.card)
        val bookInformation: TextView = view.findViewById(R.id.book_information)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitListItem {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_unit, parent, false)
        return UnitListItem(view)
    }

    fun setContext(context: Context) {
        mContext = context
    }

    override fun getItemCount(): Int = mDataset.size

    override fun onBindViewHolder(holder: UnitListItem, position: Int) {
        holder.card.setOnClickListener {
            // val intent = Intent(mContext, ThirdScreen::class.java)
            //mContext.startActivity(intent)
        }
        mDataset[position].apply {
            holder.title.text = title
            holder.region.text = region.toString()
            holder.description.text = description
            holder.price.text = CURRENCY + price
            holder.rating.numStars = rating.toInt()
            if (isBooked) {
                holder.bookInformation.visibility = VISIBLE
            } else {
                holder.bookInformation.visibility = GONE
            }
            val ratingBar = holder.rating
            val stars = ratingBar.progressDrawable as LayerDrawable
            stars.setTint(ContextCompat.getColor(ratingBar.context, R.color.colorAccent))
            val imageView = holder.image
            if (!image.isEmpty()) {
                Glide.with(imageView.context)
                    .load(image)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            }
        }
    }
}