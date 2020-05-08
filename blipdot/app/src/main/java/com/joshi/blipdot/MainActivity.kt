package com.joshi.blipdot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsoluteLayout
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var yellowBtn = findViewById<Button>(R.id.yellowBtn)
        var blackBackground = findViewById<AbsoluteLayout>(R.id.blackBackground)

        blackBackground.setOnClickListener() {
            Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show()
        }

        yellowBtn.setOnClickListener() {

        }
    }
}
