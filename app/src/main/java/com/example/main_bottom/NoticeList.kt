package com.example.main_bottom

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoticeList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notice_viewer)

        // 뒤로가기 버튼 처리
        val backBtn = findViewById<ImageButton>(R.id.backbtn)
        backBtn.setOnClickListener {
            onBackPressed()
        }

        val noticeTitle = intent.getStringExtra("noticeTitle")
        val noticeName = intent.getStringExtra("noticeName")
        val noticeDate = intent.getStringExtra("noticeDate")
        val noticeContent = intent.getStringExtra("noticeContent")

        val noticeTitleView = findViewById<TextView>(R.id.noticeTitle)
        val noticeNameView = findViewById<TextView>(R.id.noticeName)
        val noticeDateView = findViewById<TextView>(R.id.noticeDate)
        val noticeContentView = findViewById<TextView>(R.id.noticeContent)

        noticeTitleView.text = noticeTitle
        noticeNameView.text = noticeName
        noticeDateView.text = noticeDate
        noticeContentView.text = noticeContent
    }
}
