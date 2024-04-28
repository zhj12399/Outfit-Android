package cn.zhj12399.outfit.HomePages

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CenterTabsAdapter(
    fa: FragmentActivity,
    val fragmentList: List<Fragment>, //要显示的Fragment集合
    val titleList: List<String>  //每个Fragment对应的Tab页标题
) : FragmentStateAdapter(fa) {
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }
}