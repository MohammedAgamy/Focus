package com.agamy.focus.presentation.components

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.agamy.focus.R
import com.agamy.focus.utils.Phase

class TimerService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    @RequiresPermission(Manifest.permission.VIBRATE)
    @SuppressLint("ForegroundServiceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val phase = intent?.getStringExtra("phase") ?: "FOCUS"
        createChannel()
        startForeground(1, buildNotification(phase))
        playSound()
        vibrate()
        stopSelf()
        return START_NOT_STICKY
    }

    private fun buildNotification(phase: String): Notification {
        val text = if (phase == "FOCUS") "Focus done! Take a break."
        else "Break over! Time to focus."
        return NotificationCompat.Builder(this, "pomodoro")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("Pomodoro")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val ch = NotificationChannel("pomodoro", "Pomodoro", NotificationManager.IMPORTANCE_HIGH)
        getSystemService(NotificationManager::class.java).createNotificationChannel(ch)
    }

    private fun playSound() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.lithe_fall_back)
        mediaPlayer.setOnCompletionListener {
            it.release()
        }

        mediaPlayer.start()
    }


    @RequiresPermission(Manifest.permission.VIBRATE)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrate() {
        val vibrator = getSystemService(Vibrator::class.java) ?: return

        val effect = VibrationEffect.createWaveform(
            longArrayOf(0, 400, 200, 400),
            -1
        )

        vibrator.vibrate(effect)
    }

}

// Helper called from PomodoroScreen
@RequiresApi(Build.VERSION_CODES.O)
fun startTimerService(context: Context, phase: Phase) {
    val intent = Intent(context, TimerService::class.java)
        .putExtra("phase", phase.name)
    context.startForegroundService(intent)
}