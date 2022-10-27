package com.example.didinoapp.view.fragments.mymovies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.didinoapp.R
import com.example.didinoapp.databinding.FragmentMyMoviesBinding
import com.example.didinoapp.utils.showDialogAccountForFragments
import com.example.didinoapp.view.fragments.mymovies.adapter.MyMoviesFragmentAdapter

class MyMoviesFragment : Fragment() {
    private lateinit var binding : FragmentMyMoviesBinding
    private lateinit var viewPagerAdapter : MyMoviesFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerMyMoviesID.currentItem = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyMoviesBinding.inflate(layoutInflater)

        viewPagerAdapter = MyMoviesFragmentAdapter(childFragmentManager)
        binding.viewPagerMyMoviesID.adapter = viewPagerAdapter
        binding.tabLayoutMyMoviesID.setupWithViewPager(binding.viewPagerMyMoviesID)

        binding.searchBtn.setOnClickListener {
            findNavController().navigate(MyMoviesFragmentDirections.actionMyMoviesFragmentToSearchFragment())
        }

        binding.accountBtn.setOnClickListener {
            showDialogAccountForFragments(requireContext())
        }

        return binding.root
    }
}