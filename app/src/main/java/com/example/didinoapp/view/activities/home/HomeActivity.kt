package com.example.didinoapp.view.activities.home

import com.example.didinoapp.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.example.didinoapp.databinding.ActivityHomeBinding
import com.example.didinoapp.utils.setStatusColorHomeScreen

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusColorHomeScreen(window, this)
        navigationController = findNavController(R.id.home_fragment)
        binding.bottomNavigation.visibility = View.VISIBLE
        binding.bottomNavigation.setupWithNavController(navigationController)
    }
}