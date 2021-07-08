package com.example

import androidx.lifecycle.*
import com.example.network.Repository
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {
    val postId: Int = savedStateHandle.get<Int>(POST_ID_SAVED_STATE_KEY)!!

    val selectedPost = repository.getPost(postId).asLiveData()

    val createdPost = MutableLiveData<Resource<Unit>>()

    fun createPost(
        userId: Int,
        title: String,
        body: String
    ) {
        viewModelScope.launch {
            repository.createPost(userId, title, body).collect { value ->
                createdPost.value = value
            }
        }
    }

    companion object {
        private const val POST_ID_SAVED_STATE_KEY = "postId"
    }
}