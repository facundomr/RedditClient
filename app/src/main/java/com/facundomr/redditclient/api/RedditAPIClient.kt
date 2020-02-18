package com.facundomr.redditclient.api

import com.facundomr.redditclient.api.deserializer.EntryDateDeserializer
import com.facundomr.redditclient.model.EntryDate
import com.facundomr.redditclient.model.UserData
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RedditAPIClient {

    val webservice by lazy {

        val gsonBuilder = GsonBuilder()
            .registerTypeAdapter(EntryDate::class.java, EntryDateDeserializer())

        Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build().create(RedditAPIInterface::class.java)
    }

    suspend fun getEntries() = webservice.getEntries()

    suspend fun getUsers(authorIds: String): Map<String, UserData> = webservice.getUsers(authorIds)

}