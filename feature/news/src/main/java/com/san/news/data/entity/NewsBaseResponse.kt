package com.san.news.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsBaseResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("duration")
    val duration: Double? = null,
    @SerialName("result")
    val result: String? = null,
    @SerialName("return_type")
    val returnType: String? = null,
    @SerialName("subtype")
    val subtype: String? = null
) {
    @Serializable
    data class Data(
        @SerialName("newslist")
        val newslist: List<Newslist>? = null,
        @SerialName("next_items")
        val nextItems: Int? = null,
        @SerialName("sport")
        val sport: String? = null
    ) {
        @Serializable
        data class Newslist(
            @SerialName("details")
            val details: String? = null,
            @SerialName("hash_url")
            val hashUrl: String? = null,
            @SerialName("id")
            val id: String? = null,
            @SerialName("is_favorite")
            val isFavorite: Boolean? = null,
            @SerialName("pid")
            val pid: String? = null,
            @SerialName("player_image_url")
            val playerImageUrl: String? = null,
            @SerialName("pname")
            val pname: String? = null,
            @SerialName("position")
            val position: String? = null,
            @SerialName("pro_only")
            val proOnly: Boolean? = null,
            @SerialName("source")
            val source: String? = null,
            @SerialName("source_logo")
            val sourceLogo: String? = null,
            @SerialName("source_name")
            val sourceName: String? = null,
            @SerialName("source_url")
            val sourceUrl: String? = null,
            @SerialName("time")
            val time: String? = null,
            @SerialName("time_pst")
            val timePst: String? = null,
            @SerialName("title")
            val title: String? = null,
            @SerialName("type")
            val type: String? = null
        )
    }
}