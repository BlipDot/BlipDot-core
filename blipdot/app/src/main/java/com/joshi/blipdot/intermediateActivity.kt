package com.joshi.blipdot

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class intermediateActivity:AppCompatActivity() {
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milli_seconds: Long = 0L
    private var mediaPlayer: MediaPlayer? = null

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                var intent = Intent(this@intermediateActivity, MainActivity::class.java)
                startActivity(intent)
            }
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                val countDownText = findViewById<TextView>(R.id.countDownText)
                time_in_milli_seconds /= 1000L
                countDownText.text = time_in_milli_seconds.toString()
            }
        }
        countdown_timer.start()
        isRunning = true
    }

    private fun popSoundEffect(button: Button, sound: Int) {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer = MediaPlayer.create(this, R.raw.pop_sound)
            mediaPlayer?.start()
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.pop_sound)
            mediaPlayer?.start()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intermediate)
        val skipBtn = findViewById<Button>(R.id.skipBtn)
        val layout = findViewById<ConstraintLayout>(R.id.background)

        layout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        startTimer(5000)
        skipBtn.setOnClickListener() {
            popSoundEffect(skipBtn, sound = R.raw.pop_sound)
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}