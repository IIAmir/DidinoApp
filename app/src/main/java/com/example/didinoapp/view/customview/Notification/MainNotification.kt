package com.example.didinoapp.view.customview.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.didinoapp.R
import com.example.didinoapp.view.fragments.detail.DetailFragment

class MainNotification {

    fun sendNotification(context: Context, titleContent: String, TextContent: String) {

        createNotificationChannel(context)

        val notifyIntent = Intent(context, DetailFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notifyPendingIntent =
            PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val bitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.main_icon)

        val builder =
            NotificationCompat.Builder(context, "1")
                .setSmallIcon(R.drawable.main_icon)
                .setContentTitle(titleContent)
                .setContentText(TextContent)
                .setContentIntent(notifyPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(101, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        val name = ".."
        val desc = ".."
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("1", name, importance)
        channel.description = desc
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

}