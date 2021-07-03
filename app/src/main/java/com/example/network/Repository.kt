package com.example.network

import com.example.database.PostDao
import com.example.models.Post
import com.example.util.Resource
import com.example.util.insertResource
import com.example.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
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

    fun getPost(postId: Int): Flow<Resource<Post>> {
        return networkBoundResource(
            query = { postDao.selectDistinctUntilChanged(postId) },
            fetch = { webservice.getPost(postId) },
            saveFetchResult = { post -> postDao.insertOne(post) }
        )
    }

    fun createPost(
        userId: Int,
        title: String,
        body: String
    ): Flow<Resource<Unit>> {
        return insertResource(
            cache = {  },
            remote = { webservice.createPost(userId, title, body) }
        )
    }
}