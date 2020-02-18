package com.facundomr.redditclient.model

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EntriesResponse(val kind: String, val data: RedditDataResponse)

data class RedditDataResponse(val after: String, val before: String, val children: List<Entry>)

data class Entry(val data: EntryData)

data class EntryData(
    val id: String, val title: String, @SerializedName("author_fullname") val author: String,
    val thumbnail: String?, val created: EntryDate, var read: Boolean = false,
    @SerializedName("num_comments") val comments: Long, var username : String
) : Serializable

data class EntryDate(val label: String) : Serializable