package com.example.didinoapp.view.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.didinoapp.data.api.ApiClient
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.data.network.CheckConnection
import com.example.didinoapp.databinding.ActivitySplashScreenBinding
import com.example.didinoapp.utils.setPostDelayed
import com.example.didinoapp.utils.setStatusColorSplashScreen
import com.example.didinoapp.view.activities.home.HomeActivity
import com.example.didinoapp.view.activities.network.NetworkLostActivity
import com.example.didinoapp.view.customview.Notification.NotificationWorker
import com.example.didinoapp.view.customview.Notification.NotifyViewModel
import com.example.didinoapp.view.customview.Notification.NotifyViewModelFactory

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var checkConnection: CheckConnection
    private lateinit var notifyViewModel: NotifyViewModel
    var movieName :String? = null
    var movieDetail :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check Connection And Set Activity
        checkNetworkConnection()
        setUpViewModel()

        notifyViewModel.getNotify()

        notifyViewModel.notifyLiveData.observe(this){
            getDataAndSetToNotify(it)
        }

        // Change Status Color
        setStatusColorSplashScreen(window, this)
    }

    private fun setUpViewModel() {
        notifyViewModel = ViewModelProvider(
            this,
            NotifyViewModelFactory(ApiClient.apiService)
        )[NotifyViewModel::class.java]
    }

    private fun checkNetworkConnection() {
        checkConnection = CheckConnection()

        if (checkConnection.isOnline(this)) {
            setPostDelayed({
                onAvailable()
                finish()
            }, 2000)
        } else {
            setPostDelayed({
                onLost()
                finish()
            }, 2000)
        }
    }

    private fun onAvailable() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun onLost() {
        startActivity(Intent(this, NetworkLostActivity::class.java))
    }

    private fun getDataAndSetToNotify(data:MutableList<MovieAndSeriesModel>){
        if (data[data.size -1].detail_gallery != null){
            movieName = data[data.size -1].name_persian_SE
            movieDetail = data[data.size -1].detail_gallery

            notificationWorkerReq()
        }else{

        }
    }


    private fun notificationWorkerReq() {
        val workerManager = WorkManager.getInstance(this)
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED).build()

        val data =
            Data.Builder().putString("title", movieName).putString("message", movieDetail).build()
        val work =
            OneTimeWorkRequest.Builder(NotificationWorker::class.java).setConstraints(constraints)
                .setInputData(data)
                .build()

        workerManager.enqueue(work)
    }
}