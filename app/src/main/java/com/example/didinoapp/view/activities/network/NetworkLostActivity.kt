package com.example.didinoapp.view.activities.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.didinoapp.data.network.CheckConnection
import com.example.didinoapp.databinding.ActivityNetworkLostBinding
import com.example.didinoapp.utils.setPostDelayed
import com.example.didinoapp.utils.toastShort
import com.example.didinoapp.view.activities.home.HomeActivity

class NetworkLostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNetworkLostBinding
    private lateinit var checkConnection: CheckConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNetworkLostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkConnection.setOnClickListener {
            checkNetworkConnection()
        }
    }

    private fun checkNetworkConnection() {
        checkConnection = CheckConnection()

        if (checkConnection.isOnline(this)) {
            setPostDelayed({
                toastShort("اتصال برقرار شد.")
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }, 2000)
        } else {
            toastShort("اتصال برقرار نشد!")
        }
    }
}