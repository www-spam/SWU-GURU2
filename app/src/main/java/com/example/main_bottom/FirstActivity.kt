package com.example.main_bottom



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


class FirstActivity : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqliteDB: SQLiteDatabase

    lateinit var backbtn: ImageButton
    lateinit var login_ID: EditText
    lateinit var login_PW: EditText
    lateinit var login_login: ImageButton
    lateinit var login_register: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        backbtn = findViewById(R.id.backbtn)
        login_ID = findViewById(R.id.login_ID)
        login_PW = findViewById(R.id.login_PW)
        login_login = findViewById(R.id.longbtn)
        login_register = findViewById(R.id.login_register)

        dbManager = DBManager(this, "User", null, 1)
        sqliteDB = dbManager.readableDatabase

        backbtn = findViewById(R.id.backbtn)
        backbtn.setOnClickListener() {
            finish()
        }

        login_login.setOnClickListener() {
            var strID: String = login_ID.text.toString()
            var strPassword: String = login_PW.text.toString()
            var name: String = ""

            var cursor: Cursor = sqliteDB.rawQuery("select * from User where id = '$strID' and password = '$strPassword';", null)

            while (cursor.moveToNext()) {
                var validID: String = cursor.getString(0)
                name = cursor.getString(1)
                var validPassword: String = cursor.getString(2)
            }

            if(cursor.count == 1) {
                val userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                val editor = userInfo.edit()
                editor.putString("userID", strID)
                editor.putString("userName", name)
                editor.apply()

                val intent = Intent(this@FirstActivity, MainActivity::class.java)
                startActivity(intent)

                cursor.close()
                sqliteDB.close()

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }   else {
                if(strID.isEmpty() || strPassword.isEmpty()) {
                    Toast.makeText(this@FirstActivity, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }   else {
                    Toast.makeText(this@FirstActivity, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        login_register.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}