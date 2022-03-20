package com.bws.izharassignment.ui.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common.arrDataStateWise
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_covid.view.*

class StateWiseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_covid, container, false)

        view.rvCovidData.layoutManager = LinearLayoutManager(context)

        val dividerDrawable =
            context?.let { ContextCompat.getDrawable(it, R.drawable.line_divider) }
        view.rvCovidData.addItemDecoration(DividerItemDecoration(dividerDrawable))

        if (arrDataStateWise.isNotEmpty()) {
            view.txtNoDataFound.visibility = View.GONE
            val adapter = StateWiseAdapter(arrDataStateWise)
            view.rvCovidData.adapter = adapter
            adapter.notifyDataSetChanged()
        }else{
            view.txtNoDataFound.text = "Data not found"
            view.txtNoDataFound.visibility = View.VISIBLE
        }

        return view
    }
}