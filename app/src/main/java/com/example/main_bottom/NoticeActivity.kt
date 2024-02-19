package com.example.main_bottom

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NoticeActivity : AppCompatActivity() {

    private lateinit var noticeListView: ListView
    private lateinit var adapter: NoticeListAdapter
    private lateinit var noticeList: MutableList<Notice>
    companion object {
        var userID: String? = null
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notice_activity)

        val backBtn = findViewById<ImageButton>(R.id.backbtn)
        backBtn.setOnClickListener {
            onBackPressed()
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        userID = intent.getStringExtra("userID")

        noticeListView = findViewById(R.id.noticeListView)
        noticeList = mutableListOf()
        adapter = NoticeListAdapter(applicationContext, noticeList)
        noticeListView.adapter = adapter

        // Load data from the database and populate the noticeList
        loadNoticeDataFromDB()

        val notice = findViewById<LinearLayout>(R.id.notice)

        // Click event for noticeListView to show NoticeList activity with selected notice data
        noticeListView.setOnItemClickListener { _, _, position, _ ->
            val selectedNotice = noticeList[position]
            val intent = Intent(this, NoticeList::class.java).apply {
                putExtra("noticeTitle", selectedNotice.notice)
                putExtra("noticeName", selectedNotice.name)
                putExtra("noticeDate", selectedNotice.date)
                putExtra("noticeContent", selectedNotice.content)
            }
            startActivity(intent)
        }
    }

    @SuppressLint("Range")
    private fun loadNoticeDataFromDB() {
        // Create an instance of the DBManager with appropriate arguments
        val dbManager = DBManager(this, "notice.db", null, 1)

        // Get a readable database instance from the DBManager
        val db = dbManager.readableDatabase

        val query = "SELECT * FROM NOTICE"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val noticeTitle = cursor.getString(cursor.getColumnIndex("noticeTitle"))
                val noticeName = cursor.getString(cursor.getColumnIndex("noticeName"))
                val noticeDate = cursor.getString(cursor.getColumnIndex("noticeDate"))
                val noticeContent = cursor.getString(cursor.getColumnIndex("noticeContent"))

                val notice = Notice(noticeTitle, noticeName, noticeDate, noticeContent)
                noticeList.add(notice)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        adapter.notifyDataSetChanged()
    }

    private fun setButtonColors(vararg buttons: Button) {
        val colorPrimary = resources.getColor(com.google.android.material.R.color.design_default_color_primary)
        for (button in buttons) {
            button.setBackgroundColor(colorPrimary)
        }
    }
}
