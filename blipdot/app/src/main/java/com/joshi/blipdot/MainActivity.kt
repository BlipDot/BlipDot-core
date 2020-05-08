package com.joshi.blipdot

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.util.DisplayMetrics
import android.view.Display
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AbsoluteLayout
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constLayout = findViewById<ConstraintLayout>(R.id.blackBackground)

        // creating the button
        val yellowBtn = Button(this)
        // setting layout_width and layout_height using layout parameters
        yellowBtn.layoutParams = ConstraintLayout.LayoutParams(45, 45)
        yellowBtn.text = ""
        yellowBtn.setBackgroundResource(R.drawable.roundedbutton)
        yellowBtn.alpha = 1F
        yellowBtn.textSize = 15F

        // add Button to LinearLayout
        constLayout.addView(yellowBtn)

        constLayout.setOnClickListener {
            Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show()
        }

        yellowBtn.setOnClickListener {
            val animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            yellowBtn.startAnimation(animationOut)
            val animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            yellowBtn.translationX = rand(0, 50).toFloat()
            yellowBtn.translationY = rand(0, 50).toFloat()
            yellowBtn.startAnimation(animationIn)

        }
    }
}
