package cn.zhj12399.outfit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.zhj12399.outfit.loginPages.LoginActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        val button_Goin = findViewById<Button>(R.id.button_Goin)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        if (isLogin()){
//            go to home
        }

        button_Goin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }

    fun isLogin(): Boolean {
        val PREF_FILE_NAME = "user_info"
        val USER_NAME = "user_name"

        val pref = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        pref.getString(USER_NAME, "")
        return false
    }
}