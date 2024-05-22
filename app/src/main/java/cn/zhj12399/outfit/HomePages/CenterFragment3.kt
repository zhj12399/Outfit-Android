package cn.zhj12399.outfit.HomePages

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import cn.zhj12399.outfit.R
import java.util.Date
import java.util.Stack

class CenterFragment3 : Fragment() {

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

        var select_up_num = 0
        var select_down_num = 0
        var select_shoes_num = 0
        var select_hands_num = 0

        var select_brand = stringUp[select_up_num]
        var select_down = stringUp[select_down_num]
        var select_shoes = stringUp[select_shoes_num]
        var select_hands = stringUp[select_hands_num]


        return root
    }
}