package com.huantansheng.meitumeixin.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by huan on 2017/7/9.
 */
class MainTabAdapter(fragmentManager: FragmentManager, fragmentList:List<Fragment>) : FragmentPagerAdapter(fragmentManager) {

    private var list = fragmentList

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title1 = "喜欢"
        var title2 = "发现"
        var title3 = "无感"
        when (position) {
            0 -> return title1
            1 -> return title2
            else -> return title3
        }
//        return title1
    }
}