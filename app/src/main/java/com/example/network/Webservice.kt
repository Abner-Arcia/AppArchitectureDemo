package com.example.network

import com.example.models.Post
import retrofit2.http.*

interface Webservice {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/posts/{post_id}")
    suspend fun getPost(@Path("post_id") postId: Int): Post

    @POST("/posts")
    @FormUrlEncoded
    suspend fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Post
}