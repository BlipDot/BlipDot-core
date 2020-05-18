package com.joshi.blipdot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class instructionsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions)
        val backBtn = findViewById<Button>(R.id.backBtn)

        backBtn.setOnClickListener() {
            val intent = Intent(this, StartupActivity::class.java)
            startActivity(intent)
        }
    }
}