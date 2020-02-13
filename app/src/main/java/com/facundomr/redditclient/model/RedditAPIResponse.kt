package com.facundomr.redditclient.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RedditAPIResponse(val kind: String, val data: RedditDataResponse)

data class RedditDataResponse(val after: String, val before: String, val children: List<Entry>)

data class Entry(val data: EntryData)

data class EntryData(
    val title: String, @SerializedName("author_fullname") val author: String,
    val thumbnail: String?, val created: Long, val read: Boolean = false,
    @SerializedName("num_comments") val comments: Long
) : Serializable