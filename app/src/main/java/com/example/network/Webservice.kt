package com.example.network

import com.example.models.Post
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Webservice {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @POST("/posts")
    @FormUrlEncoded
    suspend fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Post
}