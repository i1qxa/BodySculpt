package com.interaps.BodySculpt.data.local.day_menu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuItemDBEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val uri:String,
    val label:String?,
    val imgRegular:String?,
    val imgSmall:String?,
    val calories:Int,
    val carbs:Int,
    val fat:Int,
    val protein:Int,
    val date:Long,
    val weight:Int,
)
