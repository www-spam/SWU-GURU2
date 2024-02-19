package com.example.main_bottom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast


class Recipe : AppCompatActivity() {
    lateinit var review_backbtn: ImageButton
    lateinit var registerbutton: Button
    lateinit var reviewbox: EditText
    lateinit var foodName: EditText
    lateinit var review_btn: ImageButton
    var TAG: String = "WriteReview"

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_write)

        val reviewBtn = findViewById<Button>(R.id.button2)
        reviewBtn.setOnClickListener {

            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }


        review_backbtn = findViewById(R.id.review_backbtn)
        review_backbtn.setOnClickListener() {
            finish()
        }

        registerbutton = findViewById(R.id.registerbutton)
        reviewbox = findViewById(R.id.reviewbox)
        foodName = findViewById(R.id.foodName)

        var dbManager = DBManager(this, "reviewTBL", null, 1)





        registerbutton.setOnClickListener {

            val foodname = foodName.text.toString()
            val reviewbox = reviewbox.text.toString()

            dbManager.insertReview(foodname, reviewbox)
            Toast.makeText(this, "입력 완료!", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "리뷰 데이터 삽입")
            finish()

        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_timer -> {
                val intent = Intent(this, Timer::class.java)
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
}




