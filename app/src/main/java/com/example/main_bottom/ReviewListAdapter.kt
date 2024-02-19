package com.example.main_bottom
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ReviewListAdapter(private val context: Context, private val reviewList: List<Review>) : BaseAdapter() {

    override fun getCount(): Int {
        return reviewList.size
    }

    override fun getItem(i: Int): Any {
        return reviewList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        var v = view
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.review, viewGroup, false)
        }

        val foodNameText = v!!.findViewById<TextView>(R.id.TitleText)
        val reviewText = v.findViewById<TextView>(R.id.reviewText)

        // Set the data to the respective TextViews
        foodNameText.text = reviewList[i].foodName
        reviewText.text = reviewList[i].review

        return v
    }
}
