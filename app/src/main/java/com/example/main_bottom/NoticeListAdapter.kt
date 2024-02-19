package com.example.main_bottom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NoticeListAdapter(private val context: Context, private val noticeList: List<Notice>) : BaseAdapter() {

    override fun getCount(): Int {
        return noticeList.size
    }

    override fun getItem(i: Int): Any {
        return noticeList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        var v = view
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.notice, viewGroup, false)
        }

        val noticeText = v!!.findViewById<TextView>(R.id.noticeText)
        val nameText = v.findViewById<TextView>(R.id.nameText)
        val dateText = v.findViewById<TextView>(R.id.dateText)

        noticeText.text = noticeList[i].notice
        nameText.text = noticeList[i].name
        dateText.text = noticeList[i].date

        v.setOnClickListener {
            // 클릭된 공지사항의 데이터를 NoticeList 액티비티로 전달
            val intent = Intent(context, NoticeList::class.java).apply {
                putExtra("noticeTitle", noticeList[i].notice)
                putExtra("noticeName", noticeList[i].name)
                putExtra("noticeDate", noticeList[i].date)
                putExtra("noticeContent", noticeList[i].content)
                // 액티비티 컨텍스트 밖에서 액티비티를 시작할 때 FLAG_ACTIVITY_NEW_TASK 플래그를 추가
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }

        return v
    }
}
