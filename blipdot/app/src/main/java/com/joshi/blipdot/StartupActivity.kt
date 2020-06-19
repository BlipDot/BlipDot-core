package com.joshi.blipdot

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Html
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.startupdark.*

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

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startupdark)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val instructionsBtn = findViewById<Button>(R.id.insBtn)
        val layout = findViewById<ConstraintLayout>(R.id.layout)
        val logo = findViewById<ImageView>(R.id.logo)
        val bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
        volumeControlStream = AudioManager.STREAM_MUSIC;

        layout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        logo.startAnimation(bounceAnim)

        playBtn.setOnClickListener() {
            popSoundEffect(playBtn, sound = R.raw.pop_sound)
            val playIntent = Intent(this, intermediateActivity::class.java)
            startActivity(playIntent)
        }

        instructionsBtn.setOnClickListener() {
            popSoundEffect(playBtn, sound = R.raw.pop_sound)
            val insIntent = Intent(this, instructionsActivity::class.java)
            startActivity(insIntent)
        }
    }
}