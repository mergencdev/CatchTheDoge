package com.mergenc.a08catchgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_game_screen.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View) {
        val intent = Intent(applicationContext, GameScreen::class.java)
        startActivity(intent)
        //finish() //onDestroy
    }

    fun exit(view: View) {
        exitProcess(-1)
    }
}