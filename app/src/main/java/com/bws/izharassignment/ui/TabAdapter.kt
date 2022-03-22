package com.bws.izharassignment.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bws.izharassignment.ui.cases.CasesTimeSeriesFragment
import com.bws.izharassignment.ui.state.StateWiseFragment
import com.bws.izharassignment.ui.test.TestFragment


class TabAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CasesTimeSeriesFragment()
            }
            1 -> {
                StateWiseFragment()
            }
            2 -> {
                TestFragment()
            }

            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}