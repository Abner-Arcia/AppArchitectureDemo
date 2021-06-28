package com.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apparchitecturedemo.R
import com.example.apparchitecturedemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnPostsHome.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_postsFragment)
        }

        binding.btnAlbumsHome.setOnClickListener {

        }

        binding.btnUsersHome.setOnClickListener {

        }
    }

}