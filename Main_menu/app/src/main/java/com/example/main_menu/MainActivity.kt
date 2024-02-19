package com.example.main_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.main_menu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewPager_food : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding .root)

        viewPager_food = findViewById(R.id.viewPager_food)
        viewPager_food.adapter = MyViewPagerAdapter(getFoods()) // 어댑터 생성
        viewPager_food.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getFoods(): ArrayList<Int> {
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
            R.drawable.food10)
    }
}