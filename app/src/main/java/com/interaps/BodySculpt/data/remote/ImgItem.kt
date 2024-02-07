package com.interaps.BodySculpt.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ImgItem(
    val url:String?,
    val width:Int?,
    val height:Int?,
)
