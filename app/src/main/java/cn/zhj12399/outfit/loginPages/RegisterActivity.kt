package cn.zhj12399.outfit.loginPages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.zhj12399.outfit.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import cn.zhj12399.outfit.entity.BaseURL
import cn.zhj12399.outfit.entity.People
import cn.zhj12399.outfit.webService.PeopleService
import java.io.IOException
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: PeopleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        val button_register = findViewById<Button>(R.id.button_register)
        val edittext_username = findViewById<EditText>(R.id.edittext_username)
        val edittext_password_one = findViewById<EditText>(R.id.edittext_password_one)
        val edittext_password_two = findViewById<EditText>(R.id.edittext_password_two)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retrofit = Retrofit.Builder().baseUrl(BaseURL.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PeopleService::class.java)

        button_register.setOnClickListener {
            val user_name = edittext_username.text.toString()
            val password_one = edittext_password_one.text.toString()
            val password_two = edittext_password_two.text.toString()

            if (user_name.isNotEmpty() && password_one.isNotEmpty() && password_two.isNotEmpty()) {
                if (password_one == password_two) {
                    thread {
                        val result = service.addPeople(People(user_name, password_one))
                        try {
                            result.execute()

                            val PREF_FILE_NAME = "user_info"
                            val USER_NAME = "user_name"
                            val pref = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.putString(USER_NAME, user_name)
                            editor.apply()

                            Looper.prepare()
                            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
//                            val intent = Intent(this, CenterActivity::class.java)
//                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                            startActivity(intent)
                            Looper.loop()
                        }catch (e: IOException) {
                            Looper.prepare()
                            Toast.makeText(this, "网络无法连接", Toast.LENGTH_LONG).show()
                            Looper.loop()
                        }
                    }
                } else {
                    Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "账户密码不能为空", Toast.LENGTH_SHORT).show()
            }
        }
    }
}