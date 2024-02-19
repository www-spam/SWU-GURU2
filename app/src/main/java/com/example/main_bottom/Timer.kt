package com.example.main_bottom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Timer
import kotlin.concurrent.timer

class Timer : AppCompatActivity() {
    private var time=0
    private var timerTask: Timer?=null
    private var isRunning = false
    private var lap=1
    private var remainingTime=0

    lateinit var fab : FloatingActionButton
    lateinit var minTextView : TextView
    lateinit var secTextView:TextView
    lateinit var labLayout: LinearLayout
    lateinit var labButton: Button
    lateinit var resetTab: FloatingActionButton
    lateinit var timeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_phone_timer, menu)

        fab = findViewById<FloatingActionButton>(R.id.fab)
        minTextView = findViewById<TextView>(R.id.minTextView)
        secTextView= findViewById<TextView>(R.id.secTextView)
        labLayout=findViewById<LinearLayout>(R.id.labLayout)
        labButton=findViewById(R.id.labButton)
        resetTab=findViewById(R.id.resetTab)
        timeEditText = findViewById(R.id.timeEditText)


        fab.setOnClickListener{
            if(isRunning){
                pause()
            }
            else{
                val userInput = timeEditText.text.toString()
                val userInputInMinutes = userInput.toInt()
                time = userInputInMinutes * 60 *1000
                updateTimerText()
                start()
            }
        }

        labButton.setOnClickListener{
            recordLapTime()
        }
        resetTab.setOnClickListener {
            reset()
        }
        timeEditText.setOnClickListener {
            time = timeEditText.text.toString().toInt()*60*1000
        }


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_recipe -> {
                val intent = Intent(this, com.example.main_bottom.Recipe::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_setting -> {
                val intent = Intent(this, Setting::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun pause(){
        isRunning=false
        fab.setImageResource(R.drawable.baseline_play_arrow_24)
        timerTask?.cancel()
        remainingTime= time
    }
    private fun start(){
        isRunning=true
        fab.setImageResource(R.drawable.baseline_pause_24)
        if (remainingTime > 0) {
            time = remainingTime
        } else {
            val userInput = timeEditText.text.toString()
            val userInputInMinutes = userInput.toInt()
            time = userInputInMinutes * 60 * 1000}

        updateTimerText()
        timerTask=timer(period=1000){
            time-=1000
            if(time<0){
                pause()
                time=0
            }
            val min=time/60000
            val sec=(time/1000)%60

            if(sec == 0 && min == 0) timerTask?.cancel()
            runOnUiThread {
                updateTimerText()
                minTextView.text="$min"
                secTextView.text = "$sec"
            }
        }
    }
    private fun updateTimerText() {
        val min = time / 60000
        val sec = (time/1000)% 60

        runOnUiThread {
            minTextView.text = "$min"
            secTextView.text = String.format("%02d",sec)
        }
    }


    private fun recordLapTime(){
        val min = time / 60000
        val sec = (time/1000)% 60
        val lapTime=String.format("%d.%02d",min,sec)
        val textView=TextView(this)
        textView.text="$lap LAB : ${lapTime}"

        labLayout.addView(textView, 0)
        lap++
    }
    private fun reset(){
        pause()
        timeEditText.text.clear()
        timerTask?.cancel()

        time=0
        isRunning=false
        fab.setImageResource(R.drawable.baseline_play_arrow_24)
        minTextView.text="0"
        secTextView.text="00"

        labLayout.removeAllViews()
        lap=1

    }
}