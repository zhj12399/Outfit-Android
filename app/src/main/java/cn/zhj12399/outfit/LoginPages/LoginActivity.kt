package cn.zhj12399.outfit.LoginPages

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
import cn.zhj12399.outfit.Entity.BaseURL
import cn.zhj12399.outfit.R
import cn.zhj12399.outfit.Entity.People
import cn.zhj12399.outfit.HomePages.CenterActivity
import cn.zhj12399.outfit.WebService.PeopleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit
    lateinit var service: PeopleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val button_goregister = findViewById<Button>(R.id.button_go_register)
        val button_login = findViewById<Button>(R.id.button_login)
        val edittext_username = findViewById<EditText>(R.id.edittext_username)
        val edittext_password = findViewById<EditText>(R.id.edittext_password)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retrofit = Retrofit.Builder().baseUrl(BaseURL.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PeopleService::class.java)

        button_login.setOnClickListener {
            val user_name = edittext_username.text.toString()
            val user_password = edittext_password.text.toString()
            if (user_name.isNotEmpty() && user_password.isNotEmpty()) {
                thread {
                    val result = service.judgePassword(People(user_name, user_password))
                    try {
                        val response = result.execute()
                        if (response.body()?.string().toBoolean()) {
                            val PREF_FILE_NAME = "user_info"
                            val USER_NAME = "user_name"
                            val pref = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.putString(USER_NAME, user_name)
                            editor.apply()

                            runOnUiThread {
                                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, CenterActivity::class.java)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this, "账户或密码错误", Toast.LENGTH_LONG).show()
                                edittext_password.setText("")
                            }
                        }
                    } catch (e: IOException) {
                        runOnUiThread {
                            Toast.makeText(this, "网络无法连接", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "账户密码不能为空", Toast.LENGTH_SHORT).show()
            }

            button_goregister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                this.startActivity(intent)
            }
        }
    }
}