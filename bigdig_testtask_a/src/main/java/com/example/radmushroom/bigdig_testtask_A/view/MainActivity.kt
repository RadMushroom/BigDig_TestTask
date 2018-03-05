package com.example.radmushroom.bigdig_testtask_A.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import com.example.radmushroom.bigdig_testtask_A.R
import com.example.radmushroom.bigdig_testtask_A.adapter.LinkListAdapter
import com.example.radmushroom.bigdig_testtask_A.controller.LinkPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : BaseActivity() {

    val pagerAdapter = LinkPagerAdapter(supportFragmentManager)

    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                invalidateOptionsMenu()
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (tabLayout.selectedTabPosition != 0){
            menuInflater.inflate(R.menu.action_bar_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.date_sort -> pagerAdapter.sort(LinkListAdapter.SortOrder.ByDate)
            R.id.status_sort -> pagerAdapter.sort(LinkListAdapter.SortOrder.ByStatus)
        }
        return super.onOptionsItemSelected(item)
    }

    interface SortSelectedListener{
        fun onSortOrderSelected(sortOrder: LinkListAdapter.SortOrder)
    }
}
