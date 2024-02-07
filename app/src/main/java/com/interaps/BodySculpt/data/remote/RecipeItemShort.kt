package com.interaps.BodySculpt.data.remote

import android.os.Parcelable
import com.interaps.BodySculpt.data.remote.IngredientItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItemShort(
    val uri:String,
    val label:String?,
    val imgRegular:String?,
    val imgSmall:String?,
    val calories:Int?,
    val totalTime:Double?,
    val ingredientsList:List<IngredientItem>
):Parcelable{

}
