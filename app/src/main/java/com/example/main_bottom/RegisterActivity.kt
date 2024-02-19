package com.example.main_bottom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.util.Log
import com.example.main_bottom.R


class RegisterActivity : AppCompatActivity() {

    lateinit var register_backbtn: ImageButton
    lateinit var register_Name: EditText
    lateinit var register_ID: EditText
    lateinit var register_PW: EditText
    lateinit var register_PW_C: EditText
    lateinit var register_btn: ImageButton

    var TAG: String = "Register"

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




        register_backbtn = findViewById(R.id.register_backbtn)
        register_backbtn.setOnClickListener() {
            finish()
        }

        register_Name = findViewById(R.id.register_Name)
        register_ID = findViewById(R.id.register_ID)
        register_PW = findViewById(R.id.register_PW)
        register_PW_C = findViewById(R.id.register_PW_C)
        register_btn = findViewById(R.id.register_btn)

        var dbManager = DBManager(this, "User", null, 1)

        register_btn.setOnClickListener() {
            val name = register_Name.text.toString()
            val id = register_ID.text.toString()
            val pw = register_PW.text.toString()
            val pw_re = register_PW_C.text.toString()

            if(name.length == 0 || id.length == 0 || pw.length == 0 || pw_re.length == 0) {
                Toast.makeText(this, "모든 정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
            else if(pw != pw_re) {
                Toast.makeText(this, "입력하신 비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                dbManager.insertUser(id, name, pw)
                Log.d(TAG, "회원 정보 삽입")
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            }
        }
    }
}