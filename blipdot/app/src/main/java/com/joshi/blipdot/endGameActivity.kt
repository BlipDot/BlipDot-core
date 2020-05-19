package com.joshi.blipdot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button

class endGameActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.endgame)
        val homeBtn = findViewById<Button>(R.id.homeButton)
        val playAgainBtn = findViewById<Button>(R.id.playAgainBtn)

        homeBtn.setOnClickListener() {
            val intent = Intent(this, StartupActivity::class.java)
            startActivity(intent)
        }

        playAgainBtn.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}