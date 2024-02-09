package com.interaps.BodySculpt.ui.menu_of_day.rv

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.interaps.BodySculpt.R

class MenuOfDayViewHolder(itemView:View):ViewHolder(itemView) {
    val ivLogo = itemView.findViewById<ImageView>(R.id.recipeLogo)
    val tvName = itemView.findViewById<TextView>(R.id.recipeName)
    val tvKcal = itemView.findViewById<TextView>(R.id.tvKcalHeader)
    val tvFat = itemView.findViewById<TextView>(R.id.tvFatHeader)
    val tvCarbo = itemView.findViewById<TextView>(R.id.tvCarboHeader)
    val tvProtein = itemView.findViewById<TextView>(R.id.tvProteinsHeader)
    val tvWeight = itemView.findViewById<TextView>(R.id.tvWeghtHeader)

}