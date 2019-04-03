package com.ahe.jobschedulertest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.PendingIntent
import android.content.Intent



class MainActivity : AppCompatActivity() {

    var REFRESH_INTERVAL:Long  = 60 * 60 * 1000
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            Util.scheduleJob(this);
        }

        button2.setOnClickListener {
            //startService(Intent(this, TestAlarmService::class.java))
            val cal = Calendar.getInstance()
            val intent = Intent(this, TestAlarmService::class.java)
            val pintent = PendingIntent.getService(this, 0, intent, 0)
            val alarm = getSystemService(ALARM_SERVICE) as AlarmManager
            // Start service every 20 seconds
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.timeInMillis, REFRESH_INTERVAL, pintent)
        }

        button3.setOnClickListener {
            stopService(Intent(baseContext, TestAlarmService::class.java))
        }
    }



}
