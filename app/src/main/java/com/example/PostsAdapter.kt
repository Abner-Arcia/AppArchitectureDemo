package com.example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apparchitecturedemo.databinding.ItemPostBinding
import com.example.interfaces.RecyclerViewInterface
import com.example.models.Post

class PostsAdapter (
    private val posts: List<Post>,
    private val listener: RecyclerViewInterface
) : RecyclerView.Adapter<PostsAdapter.PostHolder>() {

    inner class PostHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post = posts[position]
        holder.binding.tvUserIdItemPost.text = post.userId.toString()
        holder.binding.tvIdItemPost.text = post.id.toString()
        holder.binding.tvTitleItemPost.text = post.title
        holder.binding.tvBodyItemPost.text = post.body
        holder.binding.clItemPost.setOnClickListener {
            listener.onRecyclerViewItemClickListener(position)
        }
    }

    override fun getItemCount() = posts.size
}