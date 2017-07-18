package com.huantansheng.meitumeixin.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.huantansheng.meitumeixin.R
import com.huantansheng.meitumeixin.constants.Constants
import com.huantansheng.meitumeixin.main.fragment.imagelist.ImageListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var fragmentList = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        var likedListFragment = ImageListFragment.newInstance(Constants.IMAGE_LIST_LIKED)
        var findListFragment = ImageListFragment.newInstance(Constants.IMAGE_LIST_FIND)
        var unLikedListFragment = ImageListFragment.newInstance(Constants.IMAGE_LIST_UNLIKED)
        fragmentList.add(likedListFragment)
        fragmentList.add(findListFragment)
        fragmentList.add(unLikedListFragment)
        var tabAdapter = MainTabAdapter(supportFragmentManager, fragmentList)
        view_pager.adapter = tabAdapter
        view_pager.currentItem = 0
        view_pager.offscreenPageLimit = fragmentList.size - 1
        tab_Layout.setupWithViewPager(view_pager)

    }
}
