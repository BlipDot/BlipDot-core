package com.joshi.blipdot

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.DisplayMetrics
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score:Int = 0
    private var mediaPlayer: MediaPlayer? = null
    private var sound: Int = 0
    private var backgroundTouchCount: Int = 0
    private var elapsedSeconds: Double = 0.0
    private var whichButtonCreate: Int = 0
    private var gameWidth: Int = 0
    private var gameHeight: Int = 0
    var START_MILLI_SECONDS = 1000L
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L

    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

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

    private fun onclickAnim(view:Button, gameWidth:Int, gameHeight:Int, time_in_seconds: Long) {
        val animationOut = AnimationUtils.loadAnimation(this, R.anim.pop_out)
        view.startAnimation(animationOut)
        val animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        btnDisplay(view, time_in_seconds)
        view.startAnimation(animationIn)
    }

    private fun clickMissAnim(view: Button, gameWidth: Int, gameHeight: Int, time_in_seconds: Long) {
        val animationOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        view.startAnimation(animationOut)
        val animationIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        btnDisplay(view, time_in_seconds)
        view.startAnimation(animationIn)
    }

    private fun buttonCreate(view:Button, drawable:Int, layout: ConstraintLayout) {
        view.layoutParams = ConstraintLayout.LayoutParams(70, 70)
        view.text = ""
        view.setBackgroundResource(drawable)
        view.alpha = 1F
        view.textSize = 15F
        layout.addView(view)
        sound = R.raw.pop_sound
    }

    private fun redbuttonCreate(view:Button, drawable:Int, viewXCoordinate:Int, viewYCoordinate:Int, layout: ConstraintLayout) {
        view.layoutParams = ConstraintLayout.LayoutParams(75, 75)
        view.text = ""
        view.setBackgroundResource(drawable)
        view.alpha = 1F
        view.textSize = 15F
        view.x = viewXCoordinate.toFloat()
        view.y = viewYCoordinate.toFloat()
        layout.addView(view)
        sound = R.raw.pop_sound
    }

    private fun redBtnClickListener(view: Button) {
        view.setOnClickListener() {
            popSoundEffect(view, sound)
            onclickAnim(view, gameWidth, gameHeight, time_in_milli_seconds)
            score++
        }
    }

    private fun btnClickListener(view: Button) {
        view.setOnClickListener() {
            popSoundEffect(view, sound)
            onclickAnim(view, gameWidth, gameHeight, time_in_milli_seconds)
            score += 5
        }
    }

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                //clickMissAnim(view, gameWidth, gameHeight)
                Toast.makeText(this@MainActivity, "Change Button position", Toast.LENGTH_SHORT).show()
            }
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
            }
        }
        countdown_timer.start()
        isRunning = true
    }

    private fun btnDisplay(view: Button, time_in_seconds: Long) {
        var viewXCoordinate = rand(70, gameWidth)
        var viewYCoordinate = rand(70, gameHeight)
        view.translationX = viewXCoordinate.toFloat()
        view.translationY = viewYCoordinate.toFloat()
        if(!isRunning) {
            startTimer(time_in_seconds)
        } else {
            pauseTimer()
            resetTimer()
        }
    }

    private fun pauseTimer() {
        countdown_timer.cancel()
        isRunning = false
    }

    private fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
        startTimer(time_in_milli_seconds * 3)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        volumeControlStream = AudioManager.STREAM_MUSIC;
        val constLayout = findViewById<ConstraintLayout>(R.id.blackBackground)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        time_in_milli_seconds = 3 * 1000L
        gameWidth = width - 70
        gameHeight = height - 150

        val tStart = System.currentTimeMillis()

        val yellowBtn = Button(this)
        val background1 = R.drawable.roundedbutton1
        buttonCreate(yellowBtn, background1, constLayout)
        btnDisplay(yellowBtn, time_in_milli_seconds)

        val greenBtn = Button(this)
        val background2 = R.drawable.roundedbutton2
        buttonCreate(greenBtn, background2, constLayout)
        btnDisplay(greenBtn, time_in_milli_seconds)

        val blueBtn = Button(this)
        val background3 = R.drawable.roundedbutton3
        buttonCreate(blueBtn, background3, constLayout)
        btnDisplay(blueBtn, time_in_milli_seconds)

        val peachBtn = Button(this)
        val background4 = R.drawable.roundedbutton4
        buttonCreate(peachBtn, background4, constLayout)
        btnDisplay(peachBtn, time_in_milli_seconds)

        val magentaBtn = Button(this)
        val background5 = R.drawable.roundedbutton5
        buttonCreate(magentaBtn, background5, constLayout)
        btnDisplay(magentaBtn, time_in_milli_seconds)

        val redBtn1 = Button(this)
        val redBtn2 = Button(this)
        val redBtn3 = Button(this)
        val redBtn4 = Button(this)
        val redBtn5 = Button(this)
        val background6 = R.drawable.roundedbutton6

        constLayout.setOnClickListener {
            backgroundTouchCount++
            if(backgroundTouchCount == 1) {
                Toast.makeText(this, "Cross: $backgroundTouchCount", Toast.LENGTH_SHORT).show()
                redbuttonCreate(redBtn1, background6, rand(0, gameWidth), rand(0, gameHeight), constLayout)
            } else if(backgroundTouchCount == 2) {
                Toast.makeText(this, "Cross: $backgroundTouchCount", Toast.LENGTH_SHORT).show()
                redbuttonCreate(redBtn2, background6, rand(0, gameWidth), rand(0, gameHeight), constLayout)
            } else if(backgroundTouchCount == 3) {
                Toast.makeText(this, "Cross: $backgroundTouchCount", Toast.LENGTH_SHORT).show()
                redbuttonCreate(redBtn3, background6, rand(0, gameWidth), rand(0, gameHeight), constLayout)
            } else if(backgroundTouchCount == 4) {
                Toast.makeText(this, "Cross: $backgroundTouchCount", Toast.LENGTH_SHORT).show()
                redbuttonCreate(redBtn4, background6, rand(0, gameWidth), rand(0, gameHeight), constLayout)
            } else if(backgroundTouchCount == 5) {
                Toast.makeText(this, "Cross: $backgroundTouchCount", Toast.LENGTH_SHORT).show()
                redbuttonCreate(redBtn5, background6, rand(0, gameWidth), rand(0, gameHeight), constLayout)
            } else {
                val tEnd = System.currentTimeMillis()
                val tDelta = tEnd - tStart
                elapsedSeconds = tDelta / 1000.0
                Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Time Taken: $elapsedSeconds seconds", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Final Score: $score", Toast.LENGTH_SHORT).show()
            }
        }

        btnClickListener(yellowBtn)

        btnClickListener(greenBtn)

        btnClickListener(blueBtn)

        btnClickListener(peachBtn)

        btnClickListener(magentaBtn)

        redBtnClickListener(redBtn1)

        redBtnClickListener(redBtn2)

        redBtnClickListener(redBtn3)

        redBtnClickListener(redBtn4)

        redBtnClickListener(redBtn5)
    }
}
