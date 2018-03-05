package com.example.radmushroom.bigdig_testtask_A.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.radmushroom.bigdig_testtask_A.adapter.LinkListAdapter
import com.example.radmushroom.bigdig_testtask_A.view.HistoryFragment
import com.example.radmushroom.bigdig_testtask_A.view.MainActivity
import com.example.radmushroom.bigdig_testtask_A.view.StartFragment


class LinkPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val fragments = listOf(Pair("START",StartFragment()), Pair("HISTORY",HistoryFragment()))

    override fun getItem(position: Int): Fragment? {
        return fragments[position].second
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].first
    }

    fun sort(sortOrder: LinkListAdapter.SortOrder){
        (fragments[1].second as MainActivity.SortSelectedListener).onSortOrderSelected(sortOrder)
    }
}