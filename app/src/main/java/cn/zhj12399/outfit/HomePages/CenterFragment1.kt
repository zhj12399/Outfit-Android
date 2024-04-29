package cn.zhj12399.outfit.HomePages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.zhj12399.outfit.R

class CenterFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_center1, container, false)
        val textview_last_last_date = root.findViewById<TextView>(R.id.textview_last_date)
        val textview_last_date = root.findViewById<TextView>(R.id.textview_date)
        val textview_last_t = root.findViewById<TextView>(R.id.textview_last_t)
        val textview_last_1 = root.findViewById<TextView>(R.id.textview_last_1)
        val textview_last_2 = root.findViewById<TextView>(R.id.textview_last_2)


        return root
    }
}