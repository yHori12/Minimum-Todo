package com.example.robin.roomwordsample.Utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.ui.main.MainActivity

class NotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    companion object{
        private const val CHANNEL_ID = "007"
        private const val NOTIFICATION_ID = 369
        private const val RC_NOTIFICATION = 101

        const val KEY_INPUTDATA_TITLE = "Task Title"
        const val KEY_INPUTDATA_DESCRIPTION = "Task Description"

    }
    private val taskTitle: String? by lazy {
        inputData.getString(KEY_INPUTDATA_TITLE)
    }
    private val taskDescription: String? by lazy {
        inputData.getString(KEY_INPUTDATA_DESCRIPTION)
    }

    private val pendingIntent: PendingIntent by lazy {
        PendingIntent.getActivity(
            applicationContext,
            RC_NOTIFICATION,
            Intent(applicationContext, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private val notificationManager: NotificationManager by lazy {
        applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notification: Notification by lazy {
        NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_active_24px)
            .setContentTitle(taskTitle)
            .setContentText(taskDescription)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
    }


    override fun doWork(): Result {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val notificationChannel =
                    NotificationChannel(
                        CHANNEL_ID,
                        "Default Channel",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                notificationManager.createNotificationChannel(notificationChannel)
            }

            notificationManager.notify(NOTIFICATION_ID, notification)
            return Result.success()

        } catch (e: Exception) {

            return Result.failure()
        }

    }

}
