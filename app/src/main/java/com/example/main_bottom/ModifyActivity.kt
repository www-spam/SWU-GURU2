package com.example.main_bottom

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.main_bottom.R

class ModifyActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqliteDB: SQLiteDatabase

    lateinit var btnBack: ImageButton
    lateinit var id: EditText
    lateinit var name: EditText
    lateinit var pwd: EditText
    lateinit var pwdCheck: EditText
    lateinit var btnmodify: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        dbManager = DBManager(this, "User", null, 1)
        sqliteDB = dbManager.readableDatabase

        btnBack = findViewById(R.id.modify_backbtn)
        id = findViewById(R.id.modify_ID)
        name = findViewById(R.id.modify_Name)
        pwd = findViewById(R.id.modify_PW)
        pwdCheck = findViewById(R.id.modify_PW_C)
        btnmodify = findViewById(R.id.modify_btn)

        /* 뒤로가기 버튼 */
        btnBack.setOnClickListener {
            finish()
        }

        /* 비밀번호 및 이름 변경 */
        btnmodify.setOnClickListener {
            var strId: String = id.text.toString()
            var strPwd: String = pwd.text.toString()
            var strPwdCheck: String = pwdCheck.text.toString()
            var strName: String = name.text.toString()

            /* 모든 칸을 입력하지 않았을 경우 */
            if (strId.isEmpty() || strPwd.isEmpty() || strPwdCheck.isEmpty() || strName.isEmpty()) {
                Toast.makeText(this, "아이디, 이름, 비밀번호, 비밀번호 확인을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (strPwd != strPwdCheck) {
                Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                /* id가 존재하는지 DB 확인 */
                var cursor: Cursor = sqliteDB.rawQuery("select * from User where id = '$strId';", null)

                if (cursor.count == 1) {
                    /* 아이디가 존재할 경우 */
                    sqliteDB = dbManager.writableDatabase
                    sqliteDB.execSQL("UPDATE User SET name = '$strName', password = '$strPwd' WHERE id = '$strId';")
                    cursor.close()
                    sqliteDB.close()

                    Toast.makeText(this, "이름과 비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    val userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                    val editor = userInfo.edit()
                    editor.putString("userName", strName)
                    editor.apply()

                } else {
                    /* 아이디가 존재하지 않을 경우 */
                    Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}