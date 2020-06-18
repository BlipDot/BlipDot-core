package com.joshi.blipdot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.gameplay.*
import kotlinx.android.synthetic.main.intermediate.*
import org.w3c.dom.Text

class intermediateActivity:AppCompatActivity() {
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false
    var time_in_milli_seconds: Long = 0L

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                var intent1 = Intent(this@intermediateActivity, MainActivity::class.java)
                startActivity(intent1)
            }
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                val countDownText = findViewById<TextView>(R.id.countDownText)
                time_in_milli_seconds = (time_in_milli_seconds/1000L) + 1L
                countDownText.text = time_in_milli_seconds.toString()
            }
        }
        countdown_timer.start()
        isRunning = true
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intermediate)
        val layout = findViewById<ConstraintLayout>(R.id.background)
        val textView10 = findViewById<TextView>(R.id.textView10)
        val text = "<font color=#ffff99>Game</font> <font color=#98ff98>Starts</font> <font color=#6a9eb8>In:</font>"
        textView10.text = Html.fromHtml(text) as Editable?

        layout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        startTimer(3000)
    }
}