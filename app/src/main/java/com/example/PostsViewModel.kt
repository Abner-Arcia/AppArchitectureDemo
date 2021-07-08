package com.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.models.Post
import com.example.network.Repository
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val posts: LiveData<Resource<List<Post>>> = repository.getPosts().asLiveData()
}