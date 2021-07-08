package com.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apparchitecturedemo.databinding.FragmentPostsBinding
import com.example.interfaces.RecyclerViewInterface
import com.example.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), RecyclerViewInterface {

    private lateinit var binding: FragmentPostsBinding
    private val viewModel: PostsViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            when (posts.status) {
                Status.SUCCESS -> {
                    val linearLayoutManager = LinearLayoutManager(context)
                    postsAdapter = PostsAdapter(posts.data!!, this)
                    binding.rvPosts.apply {
                        layoutManager = linearLayoutManager
                        adapter = postsAdapter
                    }
                    binding.pbPosts.visibility = View.GONE
                } Status.LOADING -> {
                    binding.pbPosts.visibility = View.VISIBLE
                } Status.ERROR -> {
                    binding.pbPosts.visibility = View.GONE
                    binding.tvErrorPosts.text = posts.message
                    binding.tvErrorPosts.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onRecyclerViewItemClickListener(position: Int) {
        val directions = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(
            viewModel.posts.value!!.data!![position].id
        )
        findNavController().navigate(directions)
    }
}