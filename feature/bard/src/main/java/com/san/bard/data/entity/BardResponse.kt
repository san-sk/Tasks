package com.san.bard.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BardResponse(
    @SerialName("text")
    val text: String?
)