//package com.example.bottomnavigationbar_1
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import com.example.bottomnavigationbar_1.databinding.ActivityMainBinding
//
//class NaVi: AppCompatActivity() {
//    private lateinit var binding : ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        replaceFragment(Home())
//
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.home -> replaceFragment(Home())
//                R.id.recipe -> replaceFragment(Recipe())
//                R.id.timer -> replaceFragment(Timer())
//                R.id.setting -> replaceFragment(Setting())
//
//                else -> {
//
//
//                }
//            }
//            true
//        }
//    }
//
//    private fun replaceFragment(fragment : Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmantTransaction = fragmentManager.beginTransaction()
//        fragmantTransaction.replace(R.id.frame_layout, fragment)
//        fragmantTransaction.commit()
//    }
//}