package com.interaps.BodySculpt.ui.menu_of_day.recipe_list.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.interaps.BodySculpt.R
import com.interaps.BodySculpt.data.remote.RecipeItemShort

class RecipesRVAdapter:ListAdapter<RecipeItemShort, RecipesViewHolder>(RecipesDiffCallBack()) {

    var onBtnAddClickListener : ((RecipeItemShort) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.menu_item_recipe_list
        return RecipesViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = getItem(position)
        val context = holder.itemView.context
        with(holder){
            ivLogo.load(item.imgSmall){
                transformations(RoundedCornersTransformation(20.0f))
            }
            tvName.text = item.label
            tvKcal.text = context.getString(R.string.kcal_header, (item.calories).toString())
            tvCarbo.text = context.getString(R.string.carbo_header, (item.carbs).toString())
            tvProtein.text = context.getString(R.string.proteins_header, (item.protein).toString())
            tvFat.text = context.getString(R.string.fat_header, (item.fat).toString())
            tvWeight.text = context.getString(R.string.weight_header, (item.weight).toString())
            btnAdd.setOnClickListener {
                onBtnAddClickListener?.invoke(item)
            }
        }
    }

}