package com.interaps.BodySculpt.ui.menu_of_day.recipe_list.rv

import androidx.recyclerview.widget.DiffUtil
import com.interaps.BodySculpt.data.remote.RecipeItemShort

class RecipesDiffCallBack:DiffUtil.ItemCallback<RecipeItemShort>() {

    override fun areItemsTheSame(oldItem: RecipeItemShort, newItem: RecipeItemShort): Boolean {
        return oldItem.label == newItem.label
    }

    override fun areContentsTheSame(oldItem: RecipeItemShort, newItem: RecipeItemShort): Boolean {
        return oldItem.label == newItem.label
    }
}