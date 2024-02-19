package com.example.main_menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

private fun getFoods(): ArrayList<Int>{
    return arrayListOf<Int>(
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.food3,
        R.drawable.food4,
        R.drawable.food5,
        R.drawable.food6,
        R.drawable.food7,
        R.drawable.food8,
        R.drawable.food9,
        R.drawable.food10
    )
}

class MyViewPagerAdapter(var Foods: ArrayList<Int>) :

    RecyclerView.Adapter<MyViewPagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.fragment_photo, parent, false)) {
        val food = itemView.findViewById<ImageView>(R.id.iv_food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = Foods.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.food.setImageResource(Foods[position])
    }
}