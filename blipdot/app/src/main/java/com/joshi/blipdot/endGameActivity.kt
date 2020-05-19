package com.joshi.blipdot

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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