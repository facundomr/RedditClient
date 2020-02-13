package com.facundomr.redditclient.api

import com.facundomr.redditclient.model.RedditAPIResponse
import retrofit2.http.GET

interface RedditAPIInterface {

    @GET("top.json")
    suspend fun getEntries(): RedditAPIResponse

}