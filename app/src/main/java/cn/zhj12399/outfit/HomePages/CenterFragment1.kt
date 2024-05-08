package cn.zhj12399.outfit.HomePages

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import cn.zhj12399.outfit.Entity.BaseURL
import cn.zhj12399.outfit.R
import cn.zhj12399.outfit.WebService.OutfitService
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.Date
import kotlin.concurrent.thread

class CenterFragment1 : Fragment() {
    lateinit var retrofit: Retrofit
    lateinit var service: OutfitService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_center1, container, false)
        val textview_date = root.findViewById<TextView>(R.id.textview_date)
        val textview_last_date = root.findViewById<TextView>(R.id.textview_last_date)
        val textview_last_t = root.findViewById<TextView>(R.id.textview_last_t)
        val textview_last_1 = root.findViewById<TextView>(R.id.textview_last_1)
        val textview_last_2 = root.findViewById<TextView>(R.id.textview_last_2)

        val PREF_FILE_NAME = "user_info"
        val USER_NAME = "user_name"
        val pref = getActivity()?.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val user_name = pref?.getString(USER_NAME, "").toString()

        val now_time = Date()
        val now_time_str =
            (now_time.year + 1900).toString() + "-" + (now_time.month + 1).toString() + "-" + now_time.date
        textview_date?.setText("今天是：$now_time_str")

        retrofit = Retrofit.Builder().baseUrl(BaseURL.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(OutfitService::class.java)
        thread {
            val result = service.getLastOutfit(user_name)
            try {
                val response = result.execute()
                val last_outfit: JSONObject =
                    JSON.parse(response.body()?.string()) as JSONObject
                val last_time_str = last_outfit.getInteger("year")
                    .toString() + "-" + last_outfit.getInteger("month")
                    .toString() + "-" + last_outfit.getInteger("day").toString()

                activity?.runOnUiThread {
                    textview_last_date?.setText("上次的记录是：$last_time_str")
                    textview_last_t?.setText(
                        "上次记录的温度是：" + last_outfit.getInteger("t").toString() + "摄氏度"
                    )
                    textview_last_1?.setText(
                        "上身：" + last_outfit.getString("up") + "   下身：" + last_outfit.getString(
                            "down"
                        )
                    )
                    textview_last_2?.setText(
                        "鞋子：" + last_outfit.getString("shoes") + "   首饰：" + last_outfit.getString(
                            "hands"
                        )
                    )
                }
            } catch (e: IOException) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "网络无法连接", Toast.LENGTH_LONG).show()
                }
            }
        }
        return root
    }
}