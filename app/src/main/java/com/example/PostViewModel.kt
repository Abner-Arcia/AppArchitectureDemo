package com.example

import androidx.lifecycle.*
import com.example.models.Post
import com.example.network.Repository
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val posts: LiveData<Resource<List<Post>>> = repository.getPosts().asLiveData()

    val selectedPost = MutableLiveData<Post>()

    private val _createdPost = MutableLiveData<Resource<Unit>>()
    val createdPost = _createdPost

    fun createPost(
        userId: Int,
        title: String,
        body: String
    ) {
        viewModelScope.launch {
            repository.createPost(userId, title, body).collect { value ->
                _createdPost.value = value
            }
        }
    }

    fun selectPost(post: Post) {
        selectedPost.value = post
    }
}