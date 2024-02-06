package com.interaps.BodySculpt.data.local.weight_progress

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeightProgressDBEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val value:Double,
    val date:Long,
)
