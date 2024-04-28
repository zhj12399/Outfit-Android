package cn.zhj12399.outfit.HomePages

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.zhj12399.outfit.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CenterFragment4.newInstance] factory method to
 * create an instance of this fragment.
 */
class CenterFragment4 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textview_username = view?.findViewById<TextView>(R.id.textview_username)
        val PREF_FILE_NAME = "user_info"
        val USER_NAME = "user_name"
        val pref = getActivity()?.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val user_name = pref?.getString(USER_NAME,"").toString()
        requireActivity().runOnUiThread {
            textview_username?.setText("您好，$user_name")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_center4, container, false)

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CenterFragment4.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CenterFragment4().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}