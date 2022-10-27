package com.example.didinoapp.view.fragments.viewsmovie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.didinoapp.databinding.FragmentViewsMoviesBinding

class ViewsMoviesFragment : Fragment() {
    private lateinit var binding: FragmentViewsMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewsMoviesBinding.inflate(layoutInflater)

        return binding.root
    }
}