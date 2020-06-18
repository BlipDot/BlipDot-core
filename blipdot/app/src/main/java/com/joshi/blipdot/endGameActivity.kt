package com.joshi.blipdot

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import org.w3c.dom.Text

class endGameActivity:AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

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

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.endgame1)
        val homeBtn = findViewById<Button>(R.id.homeButton)
        val playAgainBtn = findViewById<Button>(R.id.playAgainBtn)
        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)

        var score = intent.getIntExtra("score", 0)
        val text = "<font color=#ffff99>Score</font> <font color=#98ff98>=</font> <font color=#6a9eb8>$score</font>"
        scoreDisplay.text = Html.fromHtml(text) as Editable?
        val background = findViewById<ConstraintLayout>(R.id.background)
        background.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        
        val textView9 = findViewById<TextView>(R.id.textView9)
        val textView11 = findViewById<TextView>(R.id.textView11)
        if(score <= 50) {
            val text = "<font color=#ffff99>Well</font> <font color=#98ff98>Played</font>"
            textView9.text = Html.fromHtml(text) as Editable?
            val text1 = "<font color=#6a9eb8>Try</font> <font color=#fb98f1>Again!</font>"
            textView11.text = Html.fromHtml(text1) as Editable?
        } else if(score in 51..100) {
            val text = "<font color=#ffff99>You're</font> <font color=#98ff98>Good</font> <font color=#6a9eb8>At</font> <font color=#fb98f1>\nThis!</font>"
            textView9.text = Html.fromHtml(text) as Editable?
        } else if(score in 101..149) {
            val text = "<font color=#ffff99>You're</font> <font color=#98ff98>Great</font> <font color=#6a9eb8>At</font> <font color=#fb98f1>\nThis!</font>"
            textView9.text = Html.fromHtml(text) as Editable?
        } else {
            val text = "<font color=#ffff99>Amazing!!"
            textView9.text = Html.fromHtml(text) as Editable?
        }

        homeBtn.setOnClickListener() {
            popSoundEffect(homeBtn, sound = R.raw.pop_sound)
            val intent = Intent(this, StartupActivity::class.java)
            startActivity(intent)
        }

        playAgainBtn.setOnClickListener() {
            popSoundEffect(playAgainBtn, sound = R.raw.pop_sound)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}