package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.models.Post

@Database(entities = [Post::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun postDao(): PostDao
}