package com.example.network

import com.example.database.PostDao
import com.example.models.Post
import com.example.util.Resource
import com.example.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val webservice: Webservice,
    private val postDao: PostDao
) {

    fun getPosts(): Flow<Resource<List<Post>>> {
        return networkBoundResource(
            query = { postDao.selectAllDistinctUntilChanged() },
            fetch = { webservice.getPosts() },
            saveFetchResult = { posts -> postDao.insertMany(posts) }
        )
    }

    suspend fun createPost(
        userId: Int,
        title: String,
        body: String
    ): Resource<Post> {
        return withContext(Dispatchers.IO) {
            val createdPost = webservice.createPost(userId, title, body)
            Resource.success(createdPost)
        }
    }
}