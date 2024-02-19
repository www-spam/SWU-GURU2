package com.example.main_bottom

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.viewpager2.widget.ViewPager2

import com.example.main_bottom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewPager_food : ViewPager2
    lateinit var gotolink : Button
    lateinit var searchbar : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding .root)

        viewPager_food = findViewById(R.id.viewPager_food)
        viewPager_food.adapter = MyViewPagerAdapter(getFoods()) // 어댑터 생성
        viewPager_food.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        gotolink = findViewById(R.id.goLink)
        searchbar = findViewById(R.id.search_bar)

        gotolink.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.10000recipe.com/"))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_recipe -> {
                val intent = Intent(this, Recipe::class.java)
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