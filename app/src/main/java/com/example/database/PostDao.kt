package com.example.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface PostDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertOne(post: Post)

    @Insert(onConflict = REPLACE)
    suspend fun insertMany(posts: List<Post>)

    @Query("SELECT * FROM post WHERE id = :postId")
    fun select(postId: Int): Flow<Post>

    @Query("SELECT * FROM post")
    fun selectAll(): Flow<List<Post>>

    fun selectAllDistinctUntilChanged() = selectAll().distinctUntilChanged()
}