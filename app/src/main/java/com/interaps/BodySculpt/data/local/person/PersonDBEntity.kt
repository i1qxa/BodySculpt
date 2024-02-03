package com.interaps.BodySculpt.data.local.person

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonDBEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val gender:Int,
    val startWeight:Double,
    val wishWeight:Double,
    val height:Int
)
