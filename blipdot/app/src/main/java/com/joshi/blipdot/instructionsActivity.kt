package com.joshi.blipdot

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class instructionsActivity: AppCompatActivity() {

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
        setContentView(R.layout.gameplay)
        //val backBtn = findViewById<Button>(R.id.backBtn)

        /*backBtn.setOnClickListener() {
            popSoundEffect(backBtn, sound = R.raw.pop_sound)
            val intent = Intent(this, StartupActivity::class.java)
            startActivity(intent)
        }*/
    }
}