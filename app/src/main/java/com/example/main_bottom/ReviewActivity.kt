package com.example.main_bottom

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {


    lateinit var backbtn: ImageButton
    private lateinit var reviewListView: ListView
    private lateinit var adapter: ReviewListAdapter
    private lateinit var reviewList: MutableList<Review>

    companion object {
        var userID: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_activity)

        backbtn = findViewById(R.id.backbtn)
        backbtn.setOnClickListener() {
            finish()
        }


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        userID = intent.getStringExtra("userID")

        reviewListView = findViewById(R.id.reviewListView)
        reviewList = mutableListOf()
        adapter = ReviewListAdapter(applicationContext, reviewList)
        reviewListView.adapter = adapter

        loadReviewDataFromDB()

        val foodNameTextView = findViewById<TextView>(R.id.foodName)

        reviewListView.setOnItemClickListener { parent, _, position, _ ->
            val selectedReview = reviewList[position]
            foodNameTextView.text = selectedReview.foodName
        }

        val review = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.review)
        val reviewListView = findViewById<ListView>(R.id.reviewListView)
        val imageButton7 = findViewById<ImageButton>(R.id.imageButton7)
        val imageButton9 = findViewById<ImageButton>(R.id.imageButton9)
        val foodName = findViewById<TextView>(R.id.foodName)
    }

    private fun loadReviewDataFromDB() {
        val dbManager = DBManager(this, "reviewTBL", null, 1)
        val reviews = dbManager.getAllReviews()

        reviewList.clear()
        reviewList.addAll(reviews)
        adapter.notifyDataSetChanged()
    }
}
