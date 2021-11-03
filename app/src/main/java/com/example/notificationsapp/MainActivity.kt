package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import java.nio.file.NotLinkException
import androidx.core.app.NotificationCompat as NotificationCompat

class MainActivity : AppCompatActivity() {

    lateinit var et: EditText
    lateinit var btn: Button
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    private val notificationId=110

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et = findViewById(R.id.et)
        btn = findViewById(R.id.button)

        createNotificationChanel()
        btn.setOnClickListener {
            if(et.text.isNotBlank()){
                sendNotification()
            }else{
                Toast.makeText(applicationContext,"the filed is required",Toast.LENGTH_LONG).show()
            }

        }




    }


    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel=NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)


        }
    }

    fun sendNotification(){
        var builder = Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("My Notification")
            .setContentText(et.text.toString())


        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())

        }


    }

}