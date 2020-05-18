package com.joshi.blipdot

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartupActivity: AppCompatActivity() {
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
        setContentView(R.layout.startupdark)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val instructionsBtn = findViewById<Button>(R.id.insBtn)
        volumeControlStream = AudioManager.STREAM_MUSIC;

        playBtn.setOnClickListener() {
            popSoundEffect(playBtn, sound = R.raw.pop_sound)
            val playIntent = Intent(this, MainActivity::class.java)
            startActivity(playIntent)
        }

        instructionsBtn.setOnClickListener() {
            popSoundEffect(playBtn, sound = R.raw.pop_sound)
            val insIntent = Intent(this, instructionsActivity::class.java)
            startActivity(insIntent)
        }
    }
}