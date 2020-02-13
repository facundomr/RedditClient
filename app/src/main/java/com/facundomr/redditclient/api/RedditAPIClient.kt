package com.facundomr.redditclient.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RedditAPIClient {

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(RedditAPIInterface::class.java)
    }

    suspend fun getEntries() = webservice.getEntries()


}