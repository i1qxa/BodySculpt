package com.interaps.BodySculpt.ui.menu_of_day.rv

import androidx.recyclerview.widget.DiffUtil
import com.interaps.BodySculpt.data.local.day_menu.MenuItemDBEntity
import com.interaps.BodySculpt.data.remote.RecipeItemShort

class MenuOfDayDiffCallBack:DiffUtil.ItemCallback<MenuItemDBEntity>() {

    override fun areItemsTheSame(oldItem: MenuItemDBEntity, newItem: MenuItemDBEntity): Boolean {
        return oldItem.label == newItem.label
    }

    override fun areContentsTheSame(oldItem: MenuItemDBEntity, newItem: MenuItemDBEntity): Boolean {
        return oldItem.label == newItem.label
    }
}