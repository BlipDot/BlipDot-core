package com.joshi.blipdot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class intermediateActivity:AppCompatActivity() {
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milli_seconds: Long = 0L

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intermediate)
        val skipBtn = findViewById<Button>(R.id.skipBtn)

        startTimer(5000)
        skipBtn.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}