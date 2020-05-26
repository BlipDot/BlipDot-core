package com.joshi.blipdot

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.marginBottom

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.endgame)
        val homeBtn = findViewById<Button>(R.id.homeButton)
        val playAgainBtn = findViewById<Button>(R.id.playAgainBtn)
        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)

        var score = intent.getIntExtra("score", 0)
        scoreDisplay.text = "Score: $score"
        val background = findViewById<ConstraintLayout>(R.id.background)

        background.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        val terrible = findViewById<ImageView>(R.id.terrible)
        val ok = findViewById<ImageView>(R.id.ok)
        val bad = findViewById<ImageView>(R.id.bad)
        val good = findViewById<ImageView>(R.id.good)

        if(score <= 50) {
            terrible.visibility = View.VISIBLE
        } else if(score in 51..100) {
            bad.visibility = View.VISIBLE
        } else if(score in 101..149) {
            ok.visibility = View.VISIBLE
        } else {
            good.visibility = View.VISIBLE
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