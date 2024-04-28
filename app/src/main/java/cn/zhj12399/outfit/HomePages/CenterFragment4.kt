package cn.zhj12399.outfit.HomePages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import cn.zhj12399.outfit.LoginPages.LoginActivity
import cn.zhj12399.outfit.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CenterFragment4 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_center4, container, false)
        val textview_username = root.findViewById<TextView>(R.id.textview_username)
        val button_log_off = root.findViewById<Button>(R.id.button_log_off)
        val button_about = root.findViewById<Button>(R.id.button_about)
        val PREF_FILE_NAME = "user_info"
        val USER_NAME = "user_name"
        val pref = getActivity()?.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val user_name = pref?.getString(USER_NAME, "").toString()
        textview_username?.setText("您好，$user_name")

        button_log_off.setOnClickListener {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("确定注销账户码？")
                .setMessage("退出后您的账号仍会保留\n其它信息也不会被删除")
                .setNeutralButton("取消") { dialog, which ->

                }
                .setPositiveButton("是") { dialog, which ->
                    val editor = pref?.edit()
                    editor?.putString(USER_NAME, "")
                    editor?.apply()

                    Toast.makeText(activity, "成功下线", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    activity?.startActivity(intent)
                }
                .show()
        }
        button_about.setOnClickListener {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("关于穿搭世界")
                .setMessage(
                    "一款可记录每日穿搭的网站\n" +
                            "作者:zhj12399\n" +
                            "作者邮箱:zhj@bit.edu.cn\n" +
                            "网站地址:https://ty.zhj12399.cn:9000"
                )
                .setPositiveButton("确定") { dialog, which ->
                }
                .show()
        }
        return root
    }
}