package com.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.apparchitecturedemo.databinding.FragmentPostDetailBinding
import com.example.util.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.selectedPost.observe(viewLifecycleOwner) {
            binding.etUserIdPostDetail.setText(it.userId.toString())
            binding.etPostIdPostDetail.setText(it.id.toString())
            binding.etTitlePostDetail.setText(it.title)
            binding.etBodyPostDetail.setText(it.body)
        }
        binding.btnSavePostDetail.setOnClickListener {
            viewModel.createPost(
                binding.etUserIdPostDetail.text.toString().toInt(),
                binding.etTitlePostDetail.text.toString(),
                binding.etBodyPostDetail.text.toString()
            )
        }
        viewModel.createdPost.observe(viewLifecycleOwner) { post ->
            when (post.status) {
                Status.SUCCESS -> {
                    Snackbar.make(binding.btnSavePostDetail, "Creado ID " + post.data!!.id, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}