package com.mergenc.a08catchgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlin.system.exitProcess

class GameScreen : AppCompatActivity() {
    var score = 0
    var dogesArray = ArrayList<ImageView>()
    var handler: Handler = Handler(Looper.getMainLooper())
    var runnable = Runnable { }
    //val intentMainActivity = Intent(applicationContext, MainActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        //val intent = intent
        timeCounter()
        scoreText.text = "score: $score"

        //ImageArray
        dogesArray.add(dogeImage)
        dogesArray.add(dogeImage1)
        dogesArray.add(dogeImage2)
        dogesArray.add(dogeImage3)
        dogesArray.add(dogeImage4)
        dogesArray.add(dogeImage5)
        dogesArray.add(dogeImage6)
        dogesArray.add(dogeImage7)
        dogesArray.add(dogeImage8)
        dogesArray.add(dogeImage9)
        dogesArray.add(dogeImage10)
        dogesArray.add(dogeImage11)

        hideImages()
    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for (i in dogesArray) {
                    i.visibility = View.INVISIBLE
                }
                val visibleDoge = (0..11).random()
                dogesArray[visibleDoge].visibility = View.VISIBLE

                handler.postDelayed(runnable, 400)
            }
        }

        handler.post(runnable)
    }

    fun dogeClicked(view: View) {
        score++
        scoreText.text = "score: ${score}"
    }

    fun alertWindow() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("game over.")
        alert.setMessage(scoreText.text.toString() + "\ndo you want to try again?")

        alert.setPositiveButton("yes") { dialog, which ->
            finish()
            startActivity(intent)
        }
        alert.setNegativeButton("no") { dialog, which ->
            exitProcess(-1)
        }

        alert.show()
    }

    fun timeCounter() { //CountDownTimer
        object : CountDownTimer(10500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timeText.text = "time: 0"
                handler.removeCallbacks(runnable)

                for (i in dogesArray)
                    i.visibility = View.INVISIBLE

                alertWindow()
            }
        }.start()
    }
}