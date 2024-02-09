package com.interaps.BodySculpt.data.remote

import android.os.Parcelable
import com.interaps.BodySculpt.data.local.day_menu.MenuItemDBEntity
import kotlinx.parcelize.Parcelize
import java.util.Calendar

@Parcelize
data class RecipeItemShort(
    val uri:String,
    val label:String?,
    val imgRegular:String?,
    val imgSmall:String?,
    val calories:Int,
    val carbs:Int,
    val fat:Int,
    val protein:Int,
    val weight:Int
):Parcelable{
    fun toDBMenuItem():MenuItemDBEntity = MenuItemDBEntity(
        0, uri, label, imgRegular, imgSmall, calories, carbs, fat, protein, Calendar.getInstance().timeInMillis, weight
    )
}
