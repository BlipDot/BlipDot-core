package com.joshi.blipdot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startupdark)
        val playBtn = findViewById<Button>(R.id.playBtn)
        val instructionsBtn = findViewById<Button>(R.id.insBtn)

        playBtn.setOnClickListener() {
            val playIntent = Intent(this, StartupActivity::class.java)
            startActivity(playIntent)
        }

        instructionsBtn.setOnClickListener() {
            val insIntent = Intent(this, instructionsActivity::class.java)
            startActivity(insIntent)
        }
    }
}