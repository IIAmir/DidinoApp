package com.example.didinoapp.view.customview.Notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(
    val context: Context,
    val params: WorkerParameters,
) : Worker(context, params) {

    override fun doWork(): Result {

        MainNotification().sendNotification(
            context,
            inputData.getString("title").toString(),
            inputData.getString("message").toString()
        )
        return Result.success()
    }
}