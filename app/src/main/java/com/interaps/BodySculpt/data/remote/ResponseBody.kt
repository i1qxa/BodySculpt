package com.interaps.BodySculpt.data.remote

import com.interaps.BodySculpt.data.remote.Hit
import com.interaps.BodySculpt.data.remote.Links
import kotlinx.serialization.Serializable

@Serializable
data class ResponseBody(
    val from:Int?,
    val to:Int?,
    val count:Int?,
    val _links: Links?,
    val hits:List<Hit>?,
)
