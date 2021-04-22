package com.guizaotech.listaseries.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.guizaotech.listaseries.ui.dashboard.DashboardFragment

private val TAB_TITLES = arrayOf(
        "Resumo",
        "Episódios",
        "Cronograma"

)

class DetailShowPagerAdapter(private val activity: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getItem(position: Int): Fragment {
       return DashboardFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TAB_TITLES[position]
    }
}