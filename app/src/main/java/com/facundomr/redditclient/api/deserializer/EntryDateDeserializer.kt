package com.facundomr.redditclient.api.deserializer

import android.text.format.DateUtils
import com.facundomr.redditclient.RedditClientApplication
import com.facundomr.redditclient.model.EntryDate
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class EntryDateDeserializer: JsonDeserializer<EntryDate> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EntryDate {

        val label = DateUtils.getRelativeDateTimeString(RedditClientApplication.getContext(),
            json!!.asLong * 1000,
            DateUtils.MINUTE_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0)

        return EntryDate(label.toString())
    }

}
