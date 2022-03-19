package com.bws.izharassignment.ui.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common.arrCases

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_case_time_series.view.*


class CasesTimeSeriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_case_time_series, container, false)
        view.recyCurrentOrder.layoutManager = LinearLayoutManager(context)
        val dividerDrawable =
            context?.let { ContextCompat.getDrawable(it, R.drawable.line_divider) }
        view.recyCurrentOrder.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = CasesAdapter(arrCases)
        view.recyCurrentOrder.adapter = adapter
        adapter.notifyDataSetChanged()
        return view
    }
}

