package cn.zhj12399.outfit.HomePages

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import cn.zhj12399.outfit.Entity.BaseURL
import cn.zhj12399.outfit.R
import cn.zhj12399.outfit.WebService.OutfitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.Stack
import kotlin.concurrent.thread

class CenterFragment3 : Fragment() {
    lateinit var retrofit: Retrofit
    lateinit var service: OutfitService

    private val stringUp = arrayOf("棉袄", "夹克", "卫衣", "短袖")
    private val stringDown = arrayOf("棉裤", "秋裤", "加绒", "单裤", "短裤")
    private val stringShoes = arrayOf("球鞋", "布鞋", "皮鞋")
    private val stringHands = arrayOf("遮阳帽", "冰袖", "围巾")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_center3, container, false)

        //初始化显示日期
        val now_time = Date()
        val textview_date = root.findViewById<TextView>(R.id.textview_date)
        val now_time_str =
            (now_time.year + 1900).toString() + "年" + (now_time.month + 1).toString() + "月" + now_time.date + "日"
        textview_date?.setText("$now_time_str")

        //下拉窗控件
        val spinner_up = root.findViewById<Spinner>(R.id.spinner_up)
        val spinner_down = root.findViewById<Spinner>(R.id.spinner_down)
        val spinner_shoes = root.findViewById<Spinner>(R.id.spinner_shoes)
        val spinner_hands = root.findViewById<Spinner>(R.id.spinner_hands)

        val spinner_adapter_up = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_item, stringUp
        )
        spinner_up.adapter = spinner_adapter_up
        spinner_up.setSelection(0, true)

        val spinner_adapter_down = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_item, stringDown
        )
        spinner_down.adapter = spinner_adapter_down
        spinner_down.setSelection(0, true)

        val spinner_adapter_shoes = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_item, stringShoes
        )
        spinner_shoes.adapter = spinner_adapter_shoes
        spinner_shoes.setSelection(0, true)

        val spinner_adapter_hands = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_item, stringHands
        )
        spinner_hands.adapter = spinner_adapter_hands
        spinner_hands.setSelection(0, true)

        //修改日期控件
        var select_year = now_time.year + 1900
        var select_month = now_time.month + 1
        var select_date = now_time.date
        val button_modify_date = root.findViewById<Button>(R.id.button_modify_date)
        button_modify_date.setOnClickListener {
            DatePickerDialog(
                requireActivity(), { view, selectyear, selectmonth, selectday ->
                    select_year = selectyear
                    select_month = selectmonth
                    select_date = selectday
                    textview_date.setText(select_year.toString() + "年" + (select_month + 1).toString() + "月" + select_date + "日")
                }, select_year, select_month, select_date
            ).show()
        }

        var select_t = 15
        val seekbar_t = root.findViewById<SeekBar>(R.id.seekBar)
        val textview_t = root.findViewById<TextView>(R.id.textview_t)
//        textview_t?.setText("温度：9")
        seekbar_t.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                textview_t?.setText("温度：$progress")
//                select_t = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

        var select_up_num = 0
        var select_down_num = 0
        var select_shoes_num = 0
        var select_hands_num = 0

        spinner_up.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                select_up_num = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spinner_down.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                select_down_num = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spinner_hands.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                select_hands_num = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spinner_shoes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                select_shoes_num = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val PREF_FILE_NAME = "user_info"
        val USER_NAME = "user_name"
        val pref = getActivity()?.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val user_name = pref?.getString(USER_NAME, "").toString()

        retrofit = Retrofit.Builder().baseUrl(BaseURL.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(OutfitService::class.java)


        val button_add_record = root.findViewById<Button>(R.id.button_add_record)
        button_add_record.setOnClickListener {
            var select_up = stringUp[select_up_num]
            var select_down = stringUp[select_down_num]
            var select_shoes = stringUp[select_shoes_num]
            var select_hands = stringUp[select_hands_num]
            val msg =
                select_year.toString() + "." + (select_month + 1).toString() + "." + select_date.toString() + "\n" +
                        "您选择了" + select_up + "的一杯" + "mg"
            thread {

            }
        }
        return root
    }
}