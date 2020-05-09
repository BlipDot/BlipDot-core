package com.joshi.blipdot

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    public fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    public fun onclickAnim(view:Button, gameWidth:Int, gameHeight:Int) {
        val animationOut = AnimationUtils.loadAnimation(this, R.anim.pop_out)
        view.startAnimation(animationOut)
        val animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        view.translationX = rand(50, gameWidth).toFloat()
        view.translationY = rand(50, gameHeight).toFloat()
        view.startAnimation(animationIn)
    }
    

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constLayout = findViewById<ConstraintLayout>(R.id.blackBackground)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        val gameWidth = width - 70
        val gameHeight = height - 70
        val yellowBtn = Button(this)
        yellowBtn.layoutParams = ConstraintLayout.LayoutParams(60, 60)
        yellowBtn.text = ""
        yellowBtn.setBackgroundResource(R.drawable.roundedbutton1)
        yellowBtn.alpha = 1F
        yellowBtn.textSize = 15F

        constLayout.addView(yellowBtn)

        val greenBtn = Button(this)
        greenBtn.layoutParams = ConstraintLayout.LayoutParams(60, 60)
        greenBtn.text = ""
        greenBtn.setBackgroundResource(R.drawable.roundedbutton2)
        greenBtn.alpha = 1F
        greenBtn.textSize = 15F
        greenBtn.x = (width - 70).toFloat()
        greenBtn.y = 0F

        constLayout.addView(greenBtn)

        constLayout.setOnClickListener {
            Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show()
        }

        yellowBtn.setOnClickListener {
            onclickAnim(yellowBtn, gameWidth, gameHeight)

        }

        greenBtn.setOnClickListener() {
            onclickAnim(greenBtn, gameWidth, gameHeight)
        }
    }
}
